package com.example.repository

import com.example.dao.DatabaseSingleton
import com.example.models.Message
import com.example.models.Messages
import com.example.models.User
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.ResultRow

class MessageRepo {
    private fun ResultRow.resultRowToArticle(): Message {
        return Message(
            id_message = this[Messages.id].value,
            id_user = this[Messages.id_user].value,
            id_chat = this[Messages.id_chat].value,
            text_message = this[Messages.text_message],
            timestamp = this[Messages.timestamp],
        )
    }

    /*Возвращает все объекты из таблицы*/
    suspend fun findAll(): List<Message> = DatabaseSingleton.dbQuery {
        TODO()
    }

    /*Запись в таблицу*/
    suspend fun create(message: Message): Message = DatabaseSingleton.dbQuery {
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