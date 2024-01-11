package com.example.routes

import com.example.models.UserCredential
import com.example.models.UserDTO
import com.example.plugins.encodeToBase64
import com.example.service.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.authenticationRoutes(userdb: UserService){
    route("/api/auth"){
        post("/register"){
            val user = call.receive<UserDTO>()
            if (user.password.isNotEmpty() && user.password.isNotEmpty()){
                val resp = userdb.addUser(user)
                call.respond(HttpStatusCode.Created, resp)
            }
            call.respond(HttpStatusCode.BadRequest, mapOf("status" to "User exists"))
        }
        post("/login"){
            val user = call.receive<UserCredential>()
            /*Проверка на наличие пользователя. Данная проверка является некоторой заглушкой
            так как словил некоторые проблемы с докером. Когда разберусь с проблемой, исправлю на
            польноценную проверку пользователя в БД*/
            if (user.login == "admin" && user.password == "admin"){
                val token = encodeToBase64(user.login + ":" + user.password)
                call.respond(HttpStatusCode.OK, mapOf("token" to token,
                                                        "status" to "Ok"))
            }
            call.respond(HttpStatusCode.BadRequest, mapOf("status" to "Wrong login or password"))
        }
        authenticate("auth-basic") {
            post("/logout") {
                val user = call.principal<UserIdPrincipal>()
                if (user == null){
                    call.respond(HttpStatusCode.Unauthorized, mapOf("status" to "Unauthorized"))
                }
                else {
                    call.respond(HttpStatusCode.OK, mapOf("status" to "Ok"))
                }
            }
            post("/check-token") {
                val user = call.principal<UserIdPrincipal>()
                if (user == null){
                    call.respond(HttpStatusCode.Unauthorized, mapOf("status" to "Unauthorized"))
                }
                else {
                    call.respond(HttpStatusCode.OK, mapOf("status" to "Ok"))
                }
            }
        }

    }
}