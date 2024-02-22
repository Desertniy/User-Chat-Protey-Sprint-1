package com.example.service

import com.example.models.User_Chat
import com.example.models.dto.user.UserChatFindModel
import com.example.repository.User_ChatRepo

class User_ChatService(val userChatRepo: User_ChatRepo) {

    suspend fun findAllUsers_Chats(): List<User_Chat> {
        return userChatRepo.findAll()
    }

    suspend fun addUserChat(userChat: UserChatFindModel): User_Chat {
        return userChatRepo.create(userChat)
    }


    suspend fun deleteUserChat(idUserChat: Int): Boolean {
        return userChatRepo.delete(idUserChat)
    }

    suspend fun findUserChatByModel(userChat: UserChatFindModel): User_Chat? {
        return userChatRepo.findOnesByModel(userChat)
    }
}