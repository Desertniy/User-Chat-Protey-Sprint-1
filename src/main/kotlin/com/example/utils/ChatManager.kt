package com.example.utils

import io.ktor.websocket.*
import java.util.concurrent.ConcurrentHashMap

class ChatManager {
    private val users = ConcurrentHashMap<Int, MutableMap<Int, DefaultWebSocketSession>>()

    fun addUser(idChat: Int, idUser: Int, session: DefaultWebSocketSession){
        users.computeIfAbsent(idChat) { ConcurrentHashMap()}
        users[idChat]?.put(idUser, session)
    }

    fun deleteUser(idChat: Int, idUser: Int){
        users[idChat]?.remove(idUser)
    }

    fun getConnectionUsers(idChat: Int): Map<Int, DefaultWebSocketSession>? {
        return users[idChat]?.toMap()
    }
}