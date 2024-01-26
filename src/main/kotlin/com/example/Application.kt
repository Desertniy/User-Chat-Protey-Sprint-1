package com.example

import com.example.dao.DatabaseSingleton
import com.example.plugins.*
import com.example.repository.UserRepo
import com.example.service.UserService
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    DatabaseSingleton.init(environment.config)
    val userRepo = UserRepo()
    val userService = UserService(userRepo)
    serializedConf()
    configureAuth(userService)
    configureRouting(userService)

}
