package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.IntIdTable
@Serializable
data class User(val idUser: Int,
                val login: String,
                val username: String,
                val password: String)
object Users : IntIdTable() {
    val login = varchar("login", 50)
    val username = varchar("username", 50).nullable()
    val password = varchar("password", 50)
    val token = varchar("token", 255).nullable()
}
