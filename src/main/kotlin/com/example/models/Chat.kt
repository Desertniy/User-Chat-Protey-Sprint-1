package com.example.models

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable


data class Chat(val id_chat: EntityID<Int>, val name_chat: String)

object Chats : IntIdTable() {
    val name_chat = varchar("name_chat", 50)
}
