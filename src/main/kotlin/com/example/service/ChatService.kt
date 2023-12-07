package com.example.service

import com.example.models.Chat

class ChatService {

    suspend fun findAllChats(): List<Chat> {
        TODO()
    }

    suspend fun addChat(user: Chat): Chat {
        TODO()
    }

    suspend fun update_info_chat(user: Chat): Chat {
        TODO()
    }

    suspend fun delete_chat(id: Int): Boolean {
        TODO()
    }

    suspend fun findChat(id: Int): Chat {
        TODO()
    }
}