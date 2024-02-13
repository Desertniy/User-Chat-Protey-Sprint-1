package com.example.plugins

import com.example.models.helpModels.UserCredential
import com.example.service.UserService
import com.example.utils.encodeToBase64
import io.ktor.server.application.*
import io.ktor.server.auth.*

fun Application.configureAuth(userService: UserService) {
    install(Authentication) {
        basic("auth-basic") {
            realm = "Access to the '/' path"
            validate { credentials ->
                val user = UserCredential(credentials.name,
                    credentials.password)
                val token = encodeToBase64(user.login + ":" + user.password)
                val result = userService.findUserByToken(token)
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