package com.example

import com.example.dao.DatabaseSingleton
import com.example.plugins.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    /*DatabaseSingleton.init(environment.config)*/
    serializedConf()
    configureAuth()
    configureRouting()

}
