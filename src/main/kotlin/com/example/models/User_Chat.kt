package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Table

@Serializable
data class User_Chat(val id_user: Int, val id_chat: Int)

object Users_Chats: Table(){
    val id_user = reference("id_user", Users)
    val id_chat = reference("id_chat", Chats)
    override val primaryKey = PrimaryKey(id_user, id_chat)
}
