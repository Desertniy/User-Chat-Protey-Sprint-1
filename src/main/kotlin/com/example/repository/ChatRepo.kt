package com.example.repository

import com.example.dao.DatabaseSingleton
import com.example.models.Chat
import com.example.models.Chats
import com.example.models.User
import com.example.models.Users
import org.jetbrains.exposed.sql.ResultRow

class ChatRepo {
    private fun ResultRow.resultRowToArticle(): Chat {
        return Chat(
            id_chat = this[Chats.id].value,
            name_chat = this[Chats.name_chat],
        )
    }


    /*Возвращает все объекты из таблицы*/
    suspend fun findAll(): List<Chat> = DatabaseSingleton.dbQuery {
        TODO()
    }

    /*Запись в таблицу*/
    suspend fun create(chat: Chat): Chat = DatabaseSingleton.dbQuery {
        TODO()
    }

    /*Обновление записи*/
    suspend fun update(chat: Chat): Chat = DatabaseSingleton.dbQuery {
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