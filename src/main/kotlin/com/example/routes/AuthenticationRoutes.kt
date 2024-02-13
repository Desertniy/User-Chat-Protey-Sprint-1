package com.example.routes

import com.example.models.helpModels.GetToken
import com.example.models.helpModels.UserCredential
import com.example.models.helpModels.UserDTO
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
            if (token != null)
                call.respond(HttpStatusCode.OK,
                    mapOf(
                        "status" to "Ok",
                        "token" to token
                    ))
            else
                call.respond(HttpStatusCode.BadRequest,
                    mapOf(
                        "status" to "Wrong login or password"
                    ))
        }
        post("/logout") {
            val token = call.request.headers["X-Auth-Token"]
            token?.let{
                val user = userdb.findUserByToken(token)
                if (user != null && userdb.deleteToken(user.id_user)){
                    call.respond(HttpStatusCode.OK, mapOf("status" to "Ok"))
                }
                call.respond(HttpStatusCode.Unauthorized, mapOf("status" to "Unauthorized"))
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