package com.example.routes

import com.example.models.Chat
import com.example.models.User
import com.example.models.User_Chat
import com.example.models.helpModels.ChatModel
import com.example.models.helpModels.ChatModelFullInfo
import com.example.models.helpModels.CreateMessageDTO
import com.example.models.helpModels.UserChatFindModel
import com.example.service.ChatService
import com.example.service.MessageService
import com.example.service.UserService
import com.example.service.User_ChatService
import com.example.utils.ChatManager
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun Route.chatRoutes(userdb: UserService, connectionUsers: ChatManager, userChatdb: User_ChatService, chatdb: ChatService, messagedb: MessageService){
    webSocket("/chat") {
        val id_user = call.parameters["user"]?.toIntOrNull()?: close((CloseReason(CloseReason.Codes.VIOLATED_POLICY, "No id user")))
        val id_chat = call.parameters["chat"]?.toIntOrNull()?: close(CloseReason(CloseReason.Codes.VIOLATED_POLICY, "No id chat"))
        val user = userdb.findUser(id_user as Int)
        if (user is User && userChatdb.findUserChatByModel(UserChatFindModel(id_chat as Int, id_user)) is User_Chat) {
            connectionUsers.addUser(id_chat as Int, user.id_user, this)
        }
        else {
            close(CloseReason(CloseReason.Codes.VIOLATED_POLICY, "Invalid token"))
            return@webSocket
        }
        val oldMessages = messagedb.findAllMessages(id_chat)
        if (oldMessages.isNotEmpty()){
            oldMessages.forEach{
                this.send(Json.encodeToString(it))
            }
        }
        try {
            for (frame in incoming) {
                frame as? Frame.Text ?: continue
                val message = Json.decodeFromString<CreateMessageDTO>(frame.readText())
                val createdMessage = messagedb.createMessage(message)
                connectionUsers.getConnectionUsers(id_chat as Int)?.forEach {
                    it.value.send(Json.encodeToString(createdMessage))
                }
            }
        }
        catch (e: Exception){
            close(CloseReason(CloseReason.Codes.VIOLATED_POLICY, "Invalid message"))
            return@webSocket
        }
        finally {
            connectionUsers.deleteUser(id_chat, id_user)
            close(CloseReason(CloseReason.Codes.NORMAL, "Disconnect"))
        }
    }

    route("/chat"){
        post("/create"){
            val token = call.request.headers["X-Auth-Token"]
            if (token == null) {
                call.respond(HttpStatusCode.Unauthorized, mapOf("status" to "Unauthorized"))
                return@post
            }
            val user = userdb.findUserByToken(token)
            if (user == null){
                call.respond(HttpStatusCode.Unauthorized, mapOf("status" to "Unauthorized"))
                return@post
            }
            try {
                val chat = call.receive<ChatModel>()
                if (user.id_user != chat.idCreator){
                    call.respond(HttpStatusCode.BadRequest, mapOf("status" to "Invalid User ID"))
                    return@post
                }
                val createdChat = chatdb.addChat(chat)
                userChatdb.addUser_Chat(UserChatFindModel(createdChat.idCreator, createdChat.id_chat))
                call.respond(HttpStatusCode.Created, createdChat)
            }
            catch (_:BadRequestException){
                call.respond(HttpStatusCode.BadRequest, mapOf("status" to "Unknown exception"))
            }
            
        }

        post("/join") {
            val token = call.request.headers["X-Auth-Token"]
            val idUser = call.parameters["user"]?.toInt()
            val idChat = call.parameters["chat"]?.toInt()
            if (token == null) {
                call.respond(HttpStatusCode.Unauthorized, mapOf("status" to "Unauthorized"))
                return@post
            }
            if (idUser == null || idChat == null){
                call.respond(HttpStatusCode.BadRequest, mapOf("status" to "Invalid user or chat"))
                return@post
            }
            val user = userdb.findUserByToken(token)
            if (user == null){
                call.respond(HttpStatusCode.Unauthorized, mapOf("status" to "Unauthorized"))
                return@post
            }
            if (user.id_user != idUser || chatdb.findChat(idChat) !is Chat){
                call.respond(HttpStatusCode.BadRequest, mapOf("status" to "Invalid user or chat"))
                return@post
            }
            if (userChatdb.findUserChatByModel(UserChatFindModel(idChat, idUser)) is User_Chat){
                call.respond(HttpStatusCode.Found, mapOf("status" to "User joined in Chat"))
                return@post }
            userChatdb.addUser_Chat(UserChatFindModel(idChat, idUser))
            call.respond(HttpStatusCode.Created, mapOf("status" to "Join complete"))
        }

        delete("/disconnect") {
            val idUser = call.parameters["user"]?.toInt()
            val idChat = call.parameters["chat"]?.toInt()
            if (idUser == null || idChat == null){
                call.respond(HttpStatusCode.BadRequest)
                return@delete
            }
            val user = userdb.findUser(idUser)
            if (user == null){
                call.respond(HttpStatusCode.NotFound, mapOf("status" to "Unauthorized"))
                return@delete
            }
            if (chatdb.findChat(idChat) !is Chat){
                call.respond(HttpStatusCode.NotFound)
                return@delete
            }
            val model = userChatdb.findUserChatByModel(UserChatFindModel(idChat, idUser))
            if (model !is User_Chat){
                call.respond(HttpStatusCode.NotFound)
                return@delete
            }
            userChatdb.delete_user_chat(model.id)
            call.respond(HttpStatusCode.OK)
        }

        put("/update-info") {
            val info = call.receive<ChatModelFullInfo>()
            try {
                val updateChat = chatdb.update_info_chat(info)
                if (updateChat !is Chat){
                    call.respond(HttpStatusCode.BadRequest)
                    return@put
                }
                call.respond(HttpStatusCode.Accepted, updateChat)
            }
            catch (e:Exception){
                call.respond(HttpStatusCode.Conflict)
            }
        }

        delete("/delete") {
            val idChat = call.parameters["chat"]?.toInt()
            if (idChat == null || chatdb.findChat(idChat) !is Chat){
                call.respond(HttpStatusCode.NotFound)
                return@delete
            }
            chatdb.delete_chat(idChat)
            call.respond(HttpStatusCode.OK)
        }

        post("/find-chat") {
            val idChat = call.parameters["chat"]?.toInt()
            if (idChat == null || chatdb.findChat(idChat) !is Chat){
                call.respond(HttpStatusCode.NotFound, mapOf("status" to "Not found chat"))
                return@post
            }
            call.respond(HttpStatusCode.Found, chatdb.findChat(idChat)!!)
        }


    }

}