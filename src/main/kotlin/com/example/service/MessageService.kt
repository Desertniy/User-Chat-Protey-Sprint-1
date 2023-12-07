package com.example.service

import com.example.models.Message

class MessageService {

    suspend fun findAllMessages(): List<Message> {
        TODO()
    }

    suspend fun createMessage(user: Message): Message {
        TODO()
    }


    suspend fun delete_message(id: Int): Boolean {
        TODO()
    }

    suspend fun findMessage(id: Int): Message {
        TODO()
    }
}