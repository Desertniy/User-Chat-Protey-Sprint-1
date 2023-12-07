package com.example.routes

import io.ktor.server.routing.*

fun Route.messageRoute() {
    route("/message") {

        /*Все сообщения*/
        get("/all") {
            TODO()
        }

        /* Поиск сообщения по id*/
        get("/id") {
            TODO()
        }

        /*Создание сообщения*/
        post() {
            TODO()
        }

        /*Удаление сообщения*/
        delete("/id") {
            TODO()
        }
    }
}