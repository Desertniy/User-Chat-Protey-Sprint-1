package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

@Serializable
data class User_Chat(val id: Int,
                     val id_user: Int,
                     val id_chat: Int)

object Users_Chats: IntIdTable(){
    val id_user = reference("id_user", Users, onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE)
    val id_chat = reference("id_chat", Chats, onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE)
}
