package com.example.repository

import com.example.dao.DatabaseSingleton
import com.example.models.Message
import com.example.models.Messages
import com.example.models.dto.message.CreateMessageDTO
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select

class MessageRepo {
    private fun ResultRow.resultRowToArticle(): Message {
        return Message(
            idMessage = this[Messages.id].value,
            idUser = this[Messages.id_user].value,
            idChat = this[Messages.id_chat].value,
            textMessage = this[Messages.text_message],
            timestamp = this[Messages.timestamp],
        )
    }

    /*Возвращает все объекты из таблицы*/
    suspend fun findAll(idChat: Int): List<Message> = DatabaseSingleton.dbQuery {
        Messages.select { Messages.id_chat eq idChat }.map { row ->
            Message(
                idMessage = row[Messages.id].value,
                idUser = row[Messages.id_user].value,
                idChat = row[Messages.id_chat].value,
                textMessage = row[Messages.text_message],
                timestamp = row[Messages.timestamp]
            )
        }
    }

    /*Запись в таблицу*/
    suspend fun create(message: CreateMessageDTO): Message = DatabaseSingleton.dbQuery {
        val result = Messages.insertAndGetId {
            it[id_chat] = message.idChat
            it[id_user] = message.idUser
            it[text_message] = message.textMessage
        }
        Messages.select { Messages.id eq result }.single().resultRowToArticle()
    }

    /*Поиск одной записи*/
    suspend fun findOnes(idMessage: Int): Message? = DatabaseSingleton.dbQuery {
        Messages.select { Messages.id eq idMessage }.singleOrNull()?.resultRowToArticle()
    }
}