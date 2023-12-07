package com.example.repository

import com.example.dao.DatabaseSingleton
import com.example.models.Message
import com.example.models.Messages
import com.example.models.User
import org.jetbrains.exposed.sql.ResultRow

class MessageRepo {
    private fun resultRowToArticle(row: ResultRow) = Message(
        id_message = row[Messages.id],
        id_user = row[Messages.id_user],
        id_chat = row[Messages.id_chat],
        text_message = row[Messages.text_message],
    )

    /*Возвращает все объекты из таблицы*/
    suspend fun findAll(): List<Message> = DatabaseSingleton.dbQuery {
        TODO()
    }

    /*Запись в таблицу*/
    suspend fun create(id_user: Int, id_chat: Int, text_message: String): Message = DatabaseSingleton.dbQuery {
        TODO()
    }

    /*Обновление записи*/
    suspend fun update(id: Int, text_message: String): Message = DatabaseSingleton.dbQuery {
        TODO()
    }

    /*Удаление записи*/
    suspend fun delete(id: Int): Boolean = DatabaseSingleton.dbQuery {
        TODO()
    }

    /*Поиск одной записи*/
    suspend fun findOnes(id: Int): Message = DatabaseSingleton.dbQuery {
        TODO()
    }
}