package com.example.repository

import com.example.dao.DatabaseSingleton
import com.example.models.Message
import com.example.models.Messages
import com.example.models.helpModels.CreateMessageDTO
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select

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
    suspend fun findAll(idChat: Int): List<Message> = DatabaseSingleton.dbQuery {
        Messages.select { Messages.id_chat eq idChat }.map { row ->
            Message(
                id_message = row[Messages.id].value,
                id_user = row[Messages.id_user].value,
                id_chat = row[Messages.id_chat].value,
                text_message = row[Messages.text_message],
                timestamp = row[Messages.timestamp]
            )
        }
    }

    /*Запись в таблицу*/
    suspend fun create(message: CreateMessageDTO): Message = DatabaseSingleton.dbQuery {
        val result = Messages.insertAndGetId {
            it[id_chat] = message.id_chat
            it[id_user] = message.id_user
            it[text_message] = message.text_message
        }
        Messages.select { Messages.id eq result }.single().resultRowToArticle()
    }

    /*Поиск одной записи*/
    suspend fun findOnes(idMessage: Int): Message? = DatabaseSingleton.dbQuery {
        Messages.select { Messages.id eq idMessage }.singleOrNull()?.resultRowToArticle()
    }
}