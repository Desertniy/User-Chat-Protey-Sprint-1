package com.example.service

import com.example.models.User
import com.example.models.dto.user.UserCredential
import com.example.models.dto.user.UserUpdate
import com.example.repository.UserRepo

class UserService(private val userRepo: UserRepo) {

    suspend fun findAllUsers(): List<User> {
        return userRepo.findAllUsers()
    }

    suspend fun addUser(user: UserCredential): User {
        return userRepo.create(user)
    }

    suspend fun updateInfoUser(user: UserUpdate): User? {
        return userRepo.update(user)
    }

    suspend fun deleteUser(userId: Int): Boolean {
        return userRepo.delete(userId)
    }

    suspend fun findUser(userId: Int): User? {
        return userRepo.findOnes(userId)


    }

    suspend fun updateToken(user: UserCredential): String?{
        return userRepo.updateToken(user)
    }

    suspend fun findUserByToken(token: String): User?{
        return userRepo.findToken(token)
    }

    suspend fun deleteToken(userId: Int): Boolean{
        return userRepo.deleteToken(userId)
    }
}