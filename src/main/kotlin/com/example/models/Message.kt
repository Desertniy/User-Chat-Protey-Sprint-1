package com.example.models

import com.example.utils.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

@Serializable
data class Message(val id_message: Int,
                   val id_user: Int,
                   val id_chat: Int,
                   val text_message: String,
                   @Serializable(with = LocalDateTimeSerializer::class)
                   val timestamp: LocalDateTime)

object Messages : IntIdTable() {
    val id_user = reference("id_user", Users, onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE)
    val id_chat = reference("id_chat", Chats, onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE)
    val text_message = varchar("text_message", 255)
    val timestamp = datetime("timestamp").defaultExpression(CurrentDateTime)
}
