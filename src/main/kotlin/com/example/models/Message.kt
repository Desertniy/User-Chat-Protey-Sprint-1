package com.example.models

import com.example.models.Chats.autoIncrement
import org.jetbrains.exposed.sql.*


data class Message(val id_message: Int, val id_user: Int, val id_chat: Int, val text_message: String)

object Messages : Table() {
    val id_message = integer("id").autoIncrement()
    val id_user = reference("id_user", Chats)
    val id_chat = reference("id_chat", Chats)

    override val primaryKey = PrimaryKey(id_message)
}
