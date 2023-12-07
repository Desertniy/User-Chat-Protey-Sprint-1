package com.example.routes

import io.ktor.server.routing.*

fun Route.userRoutes(){
    route("/user"){

        /*Получение всех пользователей*/
        get("/all"){
            TODO()
        }

        /*Изменение инофрмации о пользователе*/
        put{
            TODO()
        }

        /*Добавление нового пользователя*/
        post("/registation"){
            TODO()
        }

        /*Удаление пользователя*/
        delete("/{id}"){
            TODO()
        }

        get("/{id}"){
            TODO()
        }

    }
}