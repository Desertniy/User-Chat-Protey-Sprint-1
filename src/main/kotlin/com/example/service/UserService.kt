package com.example.service

import com.example.models.User

class UserService {

    suspend fun findAllUsers(): List<User> {
        TODO()
    }

    suspend fun addUser(user: User): User {
        TODO()
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