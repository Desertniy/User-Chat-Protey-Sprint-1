package com.example.routes

import com.example.models.Message
import com.example.service.MessageService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.messageRoute(dbMessage: MessageService) {
    route("/message") {

        /*Все сообщения*/
        get("/all") {
            val idChat = call.parameters["chat"]?.toInt()
            if (idChat == null){
                call.respond(HttpStatusCode.BadRequest, mapOf("status" to "Invalid id Chat"))
                return@get
            }
            val messages = dbMessage.findAllMessages(idChat)
            if (messages.isEmpty())
                call.respond(HttpStatusCode.NotFound, mapOf("status" to "No messages"))
            call.respond(HttpStatusCode.OK, messages)
        }

        /* Поиск сообщения по id*/
        get {
            val idMessage = call.parameters["message"]?.toInt()
            if (idMessage == null || dbMessage.findMessage(idMessage) !is Message){
                call.respond(HttpStatusCode.BadRequest, mapOf("status" to "Invalid id message"))
                return@get
            }
            call.respond(HttpStatusCode.OK, dbMessage.findMessage(idMessage)!!)
        }

    }
}