package com.example.plugins


import io.ktor.serialization.kotlinx.*
import io.ktor.server.application.*
import io.ktor.server.websocket.*
import kotlinx.serialization.json.Json
import java.time.Duration


fun Application.configureWebSocket(){
    install(WebSockets){
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofMinutes(15)
        contentConverter = KotlinxWebsocketSerializationConverter(Json)
    }
}