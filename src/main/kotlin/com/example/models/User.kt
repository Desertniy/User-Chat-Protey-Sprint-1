package com.example.models

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

data class User(val id_user: EntityID<Int>, val username: String, val password: String)
object Users : IntIdTable() {
    val username = varchar("username", 50)
    val password = varchar("password", 255)
}
