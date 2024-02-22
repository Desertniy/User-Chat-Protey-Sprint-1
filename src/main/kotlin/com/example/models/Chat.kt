package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.IntIdTable

@Serializable
data class Chat(val idChat: Int,
                val idCreator: Int,
                val nameChat: String)

object Chats : IntIdTable() {
    val idCreator = integer("id_creator")
    val name_chat = varchar("name_chat", 50)
}

