package com.example.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.chatRoutes(){
    route("/chats"){
        /*Все чаты*/
        get{
            TODO()
        }

        /*Добавление нового чата*/
        post{
            TODO()
        }

        /*Изменение информации о чате*/
        put("/update"){
            TODO()
        }

        /*Удаление чата*/
        delete("/{id}"){
            TODO()
        }

        /*Поиск чата*/
        get("/{id}"){
            TODO()
        }
    }
}