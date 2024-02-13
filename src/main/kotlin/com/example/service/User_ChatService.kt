package com.example.service

import com.example.models.User_Chat
import com.example.models.helpModels.UserChatFindModel
import com.example.repository.User_ChatRepo

class User_ChatService(val userChatRepo: User_ChatRepo) {

    suspend fun findAllUsers_Chats(): List<User_Chat> {
        return userChatRepo.findAll()
    }

    suspend fun addUser_Chat(userChat: UserChatFindModel): User_Chat {
        return userChatRepo.create(userChat)
    }


    suspend fun delete_user_chat(idUserChat: Int): Boolean {
        return userChatRepo.delete(idUserChat)
    }

    suspend fun findUserChatByModel(userChat: UserChatFindModel): User_Chat? {
        return userChatRepo.findOnesByModel(userChat)
    }
}