package com.example.repository

import com.example.dao.DatabaseSingleton
import com.example.models.User
import com.example.models.User_Chat
import com.example.models.Users
import com.example.models.Users_Chats
import org.jetbrains.exposed.sql.ResultRow

class User_ChatRepo {
    private fun ResultRow.resultRowToArticle(): User_Chat {
        return User_Chat(
            id_user = this[Users_Chats.id_user].value,
            id_chat = this[Users_Chats.id_chat].value,
        )
    }


    /*Возвращает все объекты из таблицы*/
    suspend fun findAll(): List<User_Chat> = DatabaseSingleton.dbQuery {
        TODO()
    }

    /*Запись в таблицу*/
    suspend fun create(userChat: User_Chat): User_Chat = DatabaseSingleton.dbQuery {
        TODO()
    }

    /*Обновление записи*/
    suspend fun update(userChat: User_Chat): User_Chat = DatabaseSingleton.dbQuery {
        TODO()
    }

    /*Удаление записи*/
    suspend fun delete(userChat: User_Chat): Boolean = DatabaseSingleton.dbQuery {
        TODO()
    }

    /*Поиск одной записи*/
    suspend fun findOnes(userChat: User_Chat): User_Chat = DatabaseSingleton.dbQuery {
        TODO()
    }
}