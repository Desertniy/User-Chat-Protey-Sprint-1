package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption

@Serializable
data class User_Chat(val id: Int,
                     val idUser: Int,
                     val idChat: Int)

object Users_Chats: IntIdTable(){
    val id_user = reference("id_user", Users, onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE)
    val id_chat = reference("id_chat", Chats, onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE)
}
