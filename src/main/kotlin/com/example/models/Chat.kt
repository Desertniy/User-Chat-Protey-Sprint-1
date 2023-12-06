package com.example.models

import org.jetbrains.exposed.sql.*


data class Chat(val id_chat: Int, val name_chat: String)

object Chats : Table() {
    val id_chat = integer("id").autoIncrement()
    val name_chat = varchar("name_chat", 50)

    override val primaryKey = PrimaryKey(id_chat)
}
