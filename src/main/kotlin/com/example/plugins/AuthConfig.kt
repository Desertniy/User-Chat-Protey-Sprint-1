package com.example.plugins

import com.example.models.User
import com.example.models.UserCredential
import com.example.service.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*

fun Application.configureAuth(userService: UserService) {
    install(Authentication) {
        basic("auth-basic") {
            realm = "Access to the '/' path"
            validate { credentials ->
                val user = UserCredential(credentials.name,
                    credentials.password)
                val token = encodeToBase64(user.login + ":" + user.password)
                val result = userService.findToken(token)
                if (result != null){
                    if (result.login == user.login && result.password == user.password)
                        return@validate UserIdPrincipal(result.login)
                    return@validate null
                }
                 return@validate null
            }
        }
    }
}