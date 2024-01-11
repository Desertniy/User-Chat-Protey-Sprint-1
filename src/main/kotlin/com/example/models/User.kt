package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
@Serializable
data class User(val id_user: Int, val username: String, val password: String)
object Users : IntIdTable() {
    val username = varchar("username", 50)
    val password = varchar("password", 255)
}
