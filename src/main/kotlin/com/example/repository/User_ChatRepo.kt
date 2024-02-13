package com.example.repository

import com.example.dao.DatabaseSingleton
import com.example.dao.DatabaseSingleton.dbQuery
import com.example.models.User
import com.example.models.User_Chat
import com.example.models.Users_Chats
import com.example.models.helpModels.UserChatFindModel
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class User_ChatRepo {
    private fun ResultRow.resultRowToArticle(): User_Chat {
        return User_Chat(
            id = this[Users_Chats.id].value,
            id_user = this[Users_Chats.id_user].value,
            id_chat = this[Users_Chats.id_chat].value,
        )
    }


    /*Возвращает все объекты из таблицы*/
    suspend fun findAll(): List<User_Chat> = dbQuery {
        Users_Chats.selectAll().map { row ->
            User_Chat(
                id = row[Users_Chats.id].value,
                id_user = row[Users_Chats.id_user].value,
                id_chat = row[Users_Chats.id_chat].value
            )
        }
    }

    /*Запись в таблицу*/
    suspend fun create(userChat: UserChatFindModel): User_Chat = dbQuery {
        val result = Users_Chats.insertAndGetId {
            it[Users_Chats.id_chat] = userChat.id_chat
            it[Users_Chats.id_user] = userChat.id_user
        }
        Users_Chats.select { Users_Chats.id eq result }.single().resultRowToArticle()
    }

    /*Удаление записи*/
    suspend fun delete(idUserChat: Int): Boolean = dbQuery {
        val result = Users_Chats.deleteWhere { Users_Chats.id eq idUserChat }
        result > 0
    }

    /*Поиск одной записи*/
    suspend fun findOnesByModel(userChat: UserChatFindModel): User_Chat? = dbQuery {
        Users_Chats.select { (Users_Chats.id_chat eq userChat.id_chat) and (Users_Chats.id_user eq userChat.id_user) }.singleOrNull()?.resultRowToArticle()
    }
}