package com.example.service

import com.example.models.Chat
import com.example.models.dto.chat.ChatModel
import com.example.models.dto.chat.ChatModelFullInfo
import com.example.repository.ChatRepo

class ChatService(val chatRepo: ChatRepo) {

    suspend fun addChat(chat: ChatModel): Chat {
        return chatRepo.create(chat)
    }

    suspend fun updateInfoChat(chat: ChatModelFullInfo): Chat? {
        return chatRepo.update(chat)
    }

    suspend fun deleteChat(id: Int){
        chatRepo.delete(id)
    }

    suspend fun findChat(idChat: Int): Chat? {
        return chatRepo.findOnes(idChat)
    }
}