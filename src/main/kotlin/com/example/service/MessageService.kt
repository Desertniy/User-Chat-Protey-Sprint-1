package com.example.service

import com.example.models.Message
import com.example.models.helpModels.CreateMessageDTO
import com.example.repository.MessageRepo

class MessageService(val messageRepo: MessageRepo) {

    suspend fun findAllMessages(idChat: Int): List<Message> {
        return messageRepo.findAll(idChat)
    }

    suspend fun createMessage(user: CreateMessageDTO): Message {
        return messageRepo.create(user)
    }

    suspend fun findMessage(idMessage: Int): Message? {
        return messageRepo.findOnes(idMessage)
    }
}