package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

@Serializable
public data class Chat(val id_chat: Int, val name_chat: String)

object Chats : IntIdTable() {
    val name_chat = varchar("name_chat", 50)
}

