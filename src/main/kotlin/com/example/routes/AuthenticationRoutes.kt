package com.example.routes

import com.example.models.GetToken
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
            if (user.login.isNotEmpty() && user.password.isNotEmpty() && user.username.isNotEmpty()){
                val resp = userdb.addUser(user)
                call.respond(HttpStatusCode.Created, resp)
            }
            call.respond(HttpStatusCode.BadRequest, mapOf("status" to "User exists"))
        }
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
                val user = userdb.findToken(token)
                if (user != null && userdb.deleteToken(user.login)){
                    call.respond(HttpStatusCode.OK, mapOf("status" to "Ok"))
                }
                call.respond(HttpStatusCode.Unauthorized, mapOf("status" to "Unauthorized"))
            }
            call.respond(HttpStatusCode.Unauthorized, mapOf("status" to "Unauthorized"))
            /*val user = call.principal<UserIdPrincipal>()
            if (user != null) {
                userdb.deleteToken(user.name)
                call.respond(HttpStatusCode.OK, mapOf("status" to "Ok"))
            }*/
        }

        post("/check-token") {
            val token = call.receive<GetToken>()
            if (token.token.isNotEmpty() && userdb.findToken(token.token) != null){
                call.respond(HttpStatusCode.OK, mapOf("status" to "Ok"))
            }
            call.respond(HttpStatusCode.BadRequest, mapOf("status" to "Invalid Token"))
        }

    }
}