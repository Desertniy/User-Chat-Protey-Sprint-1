package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import com.example.routes.*
import com.example.service.ChatService
import com.example.service.MessageService
import com.example.service.UserService
import com.example.service.User_ChatService
import com.example.utils.ChatManager

fun Application.configureRouting(userService: UserService, connectionUsers: ChatManager, chatService: ChatService, messageService: MessageService, userChatService: User_ChatService) {
    routing {
        chatRoutes(userService, connectionUsers, userChatService, chatService, messageService)
        messageRoute()
        userRoutes(userService)
        authenticationRoutes(userService)
    }
}
