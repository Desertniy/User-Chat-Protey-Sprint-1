package com.example.repository

import com.example.dao.DatabaseSingleton
import com.example.models.User
import com.example.models.User_Chat
import com.example.models.Users
import com.example.models.Users_Chats
import org.jetbrains.exposed.sql.ResultRow

class User_ChatRepo {
    private fun resultRowToArticle(row: ResultRow) = User_Chat(
        id_user = row[Users_Chats.id_user],
        id_chat = row[Users_Chats.id_chat],
    )

    /*Возвращает все объекты из таблицы*/
    suspend fun findAll(): List<User_Chat> = DatabaseSingleton.dbQuery {
        TODO()
    }

    /*Запись в таблицу*/
    suspend fun create(id_user: Int, id_chat: Int): User_Chat = DatabaseSingleton.dbQuery {
        TODO()
    }

    /*Обновление записи*/
    suspend fun update(id_user: Int, id_chat: Int): User_Chat = DatabaseSingleton.dbQuery {
        TODO()
    }

    /*Удаление записи*/
    suspend fun delete(id_user: Int, id_chat: Int): Boolean = DatabaseSingleton.dbQuery {
        TODO()
    }

    /*Поиск одной записи*/
    suspend fun findOnes(id_user: Int, id_chat: Int): User_Chat = DatabaseSingleton.dbQuery {
        TODO()
    }
}