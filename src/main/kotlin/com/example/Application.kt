package com.example

import com.example.dao.DatabaseSingleton
import com.example.plugins.*
import com.example.repository.ChatRepo
import com.example.repository.MessageRepo
import com.example.repository.UserRepo
import com.example.repository.User_ChatRepo
import com.example.service.ChatService
import com.example.service.MessageService
import com.example.service.UserService
import com.example.service.User_ChatService
import com.example.utils.ChatManager
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    DatabaseSingleton.init(environment.config)
    val userRepo = UserRepo()
    val userService = UserService(userRepo)
    val chatRepo = ChatRepo()
    val chatService = ChatService(chatRepo)
    val messageRepo = MessageRepo()
    val messageService = MessageService(messageRepo)
    val userChatRepo = User_ChatRepo()
    val userChatService = User_ChatService(userChatRepo)
    val connectionUsers = ChatManager()
    configureWebSocket()
    serializedConf()
    configureAuth(userService)
    configureRouting(userService, connectionUsers, chatService, messageService, userChatService)

}
