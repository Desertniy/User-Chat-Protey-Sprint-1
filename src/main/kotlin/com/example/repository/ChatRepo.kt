package com.example.repository

import com.example.dao.DatabaseSingleton
import com.example.models.Chat
import com.example.models.Chats
import com.example.models.User
import com.example.models.Users
import org.jetbrains.exposed.sql.ResultRow

class ChatRepo {
    private fun resultRowToArticle(row: ResultRow) = Chat(
        id_chat = row[Chats.id],
        name_chat = row[Chats.name_chat],
    )

    /*Возвращает все объекты из таблицы*/
    suspend fun findAll(): List<Chat> = DatabaseSingleton.dbQuery {
        TODO()
    }

    /*Запись в таблицу*/
    suspend fun create(name_chat: String): Chat = DatabaseSingleton.dbQuery {
        TODO()
    }

    /*Обновление записи*/
    suspend fun update(id: Int, name_chat: String): Chat = DatabaseSingleton.dbQuery {
        TODO()
    }

    /*Удаление записи*/
    suspend fun delete(id: Int): Boolean = DatabaseSingleton.dbQuery {
        TODO()
    }

    /*Поиск одной записи*/
    suspend fun findOnes(id: Int): Chat = DatabaseSingleton.dbQuery {
        TODO()
    }
}