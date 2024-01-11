package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime
import org.jetbrains.exposed.sql.javatime.timestamp
import java.time.Instant
import java.time.LocalDateTime

data class Message(val id_message: Int, val id_user: Int, val id_chat: Int, val text_message: String, val timestamp: LocalDateTime)

object Messages : IntIdTable() {
    val id_user = reference("id_user", Users)
    val id_chat = reference("id_chat", Chats)
    val text_message = varchar("text_message", 255)
    val timestamp = datetime("timestamp").defaultExpression(CurrentDateTime)
}
