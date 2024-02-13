package com.example.routes

import com.example.models.dto.user.UserCredential
import com.example.models.dto.user.UserUpdate
import com.example.service.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRoutes(userdb: UserService){
    route("/user"){

        post("/register"){
            val user = call.receive<UserCredential>()
            if (user.login.isNotEmpty() && user.password.isNotEmpty()){
                val resp = userdb.addUser(user)
                call.respond(HttpStatusCode.Created, resp)
            }
            call.respond(HttpStatusCode.BadRequest, mapOf("status" to "User exists"))
        }
        /*Получение всех пользователей*/
        get("/all"){
            val allUsers = userdb.findAllUsers()
            call.respond(HttpStatusCode.OK, allUsers)
        }

        /*Изменение инофрмации о пользователе*/
        put{
            val user = call.receive<UserUpdate>()
            val updateUser = userdb.updateInfoUser(user)
            updateUser?.let {
                call.respond(HttpStatusCode.OK, updateUser)
            }
            call.respond(HttpStatusCode.BadRequest, mapOf(
                "status" to "Incorrect info"))
        }

        /*Удаление пользователя*/
        delete{
            val id = call.parameters["id"]?.toIntOrNull()?: call.respond(HttpStatusCode.BadRequest, mapOf("status" to "Incorrect Id parameter"))
            val deleteUser = userdb.deleteUser(id as? Int)
            if (deleteUser){
                call.respond(HttpStatusCode.Found, mapOf("status" to "User Delete"))
            }
            call.respond(HttpStatusCode.BadRequest, mapOf("status" to "Invalid Id"))
        }

        get{
            val id = call.parameters["id"]?.toIntOrNull()?: call.respond(HttpStatusCode.BadRequest, mapOf("status" to "Incorrect Id parameter"))
            val user = userdb.findUser(id as? Int)
            user?.let {
                call.respond(HttpStatusCode.Found, user)
            }
            call.respond(HttpStatusCode.BadRequest, mapOf("status" to "Invalid UserId"))
        }

    }
}