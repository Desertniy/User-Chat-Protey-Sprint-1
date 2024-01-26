package com.example.service

import com.example.models.User
import com.example.models.UserCredential
import com.example.models.UserDTO
import com.example.repository.UserRepo

class UserService(private val userRepo: UserRepo) {

    suspend fun findAllUsers(): List<User> {
        TODO()
    }

    suspend fun addUser(user: UserDTO): User {
        return userRepo.create(user)
    }

    suspend fun update_info_user(user: User): User {
        TODO()
    }

    suspend fun delete_user(id: Int): Boolean {
        TODO()
    }

    suspend fun findUser(user: UserCredential): User? {
        return userRepo.findOnes(user)
    }

    suspend fun updateToken(user: UserCredential): String?{
        if (findUser(user) != null){
            return userRepo.updateToken(user)
        }
        return null
    }

    suspend fun findToken(token: String): User?{
        return userRepo.findToken(token)
    }

    suspend fun deleteToken(login: String): Boolean{
        return userRepo.deleteToken(login)
    }
}