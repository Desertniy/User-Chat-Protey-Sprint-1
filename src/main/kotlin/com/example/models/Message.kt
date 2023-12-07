package com.example.models

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable


data class Message(val id_message: EntityID<Int>, val id_user: EntityID<Int>, val id_chat: EntityID<Int>, val text_message: String)

object Messages : IntIdTable() {
    val id_user = reference("id_user", Users)
    val id_chat = reference("id_chat", Chats)
    val text_message = varchar("text_message", 255)
}
