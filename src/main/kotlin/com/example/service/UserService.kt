package com.example.service

import com.example.models.User
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

    suspend fun findUser(id: Int): User {
        TODO()
    }
}