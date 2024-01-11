package com.example.plugins

import com.example.models.User
import com.example.repository.UserRepo
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.example.routes.*
import com.example.service.UserService
import io.ktor.http.*
import io.ktor.server.auth.*

fun Application.configureRouting() {
    routing {
        authenticate("auth-basic") {
            chatRoutes()
            messageRoute()
            userRoutes()
        }
        val userRepo = UserRepo()
        val userService = UserService(userRepo)
        authenticationRoutes(userService)
    }
}
