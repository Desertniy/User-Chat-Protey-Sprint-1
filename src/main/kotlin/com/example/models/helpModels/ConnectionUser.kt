package com.example.models.helpModels

import io.ktor.websocket.*

data class ConnectionUser(val token: String, val session: DefaultWebSocketSession)
