package com.example.plugins

import com.example.models.User
import com.example.models.UserCredential
import io.ktor.server.application.*
import io.ktor.server.auth.*

fun Application.configureAuth() {
    install(Authentication) {
        basic("auth-basic") {
            realm = "Access to the '/' path"
            validate { credentials ->
                /*Исправлю логику проверки. На данный момент вышла самая базовая, потому что
                проблемы с Докером, а там лежит БД. */
                if (credentials.name == "admin" && credentials.password == "admin") {
                    UserIdPrincipal(credentials.name)
                } else {
                    null
                }
            }
        }
    }
}