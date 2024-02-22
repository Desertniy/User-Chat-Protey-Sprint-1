package com.example.routes

import com.example.models.dto.user.GetToken
import com.example.models.dto.user.UserCredential
import com.example.service.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.authenticationRoutes(userdb: UserService){
    route("/api/auth"){
        post("/login"){
            val user = call.receive<UserCredential>()
            val token = userdb.updateToken(user)
            if (token == null)
                call.respond(HttpStatusCode.BadRequest,
                    mapOf(
                        "status" to "Wrong login or password"
                    ))
            call.respond(HttpStatusCode.OK,
                mapOf(
                    "status" to "Ok",
                    "token" to token
                ))
        }
        post("/logout") {
            val token = call.request.headers["X-Auth-Token"]
            if (token == null){
                call.respond(HttpStatusCode.Unauthorized, mapOf("status" to "Unauthorized"))
                return@post
            }
            val user = userdb.findUserByToken(token)
            if (user != null && userdb.deleteToken(user.idUser)){
                call.respond(HttpStatusCode.OK, mapOf("status" to "Ok"))
            }
            call.respond(HttpStatusCode.Unauthorized, mapOf("status" to "Unauthorized"))
        }

        post("/check-token") {
            val token = call.receive<GetToken>()
            if (token.token.isNotEmpty() && userdb.findUserByToken(token.token) != null){
                call.respond(HttpStatusCode.OK, mapOf("status" to "Ok"))
            }
            call.respond(HttpStatusCode.BadRequest, mapOf("status" to "Invalid Token"))
        }

    }
}