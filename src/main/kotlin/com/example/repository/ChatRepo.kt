package com.example.repository

import com.example.dao.DatabaseSingleton
import com.example.models.Chat
import com.example.models.Chats
import com.example.models.dto.chat.ChatModel
import com.example.models.dto.chat.ChatModelFullInfo
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class ChatRepo() {
    private fun ResultRow.resultRowToArticle(): Chat {
        return Chat(
            id_chat = this[Chats.id].value,
            idCreator = this[Chats.idCreator],
            name_chat = this[Chats.name_chat],
        )
    }


    /*Возвращает все объекты из таблицы*/
    suspend fun findAll(): List<Chat> = DatabaseSingleton.dbQuery {
        TODO()
    }

    /*Запись в таблицу*/
    suspend fun create(chat: ChatModel): Chat = DatabaseSingleton.dbQuery {
        val chatId = Chats.insertAndGetId {
            it[idCreator] = chat.idCreator
            it[name_chat] = chat.nameChat
        }
        Chats.select {Chats.id eq chatId }.single().resultRowToArticle()
    }

    /*Обновление записи*/
    suspend fun update(chat: ChatModelFullInfo): Chat? = DatabaseSingleton.dbQuery {
        Chats.update({Chats.id eq chat.idChat}){
            it[name_chat] = chat.nameChat
        }
        Chats.select { Chats.id eq chat.idChat }.singleOrNull()?.resultRowToArticle()
    }

    /*Удаление записи*/
    suspend fun delete(idChat: Int) = DatabaseSingleton.dbQuery {
        Chats.deleteWhere { Chats.id eq idChat }
    }

    /*Поиск одной записи*/
    suspend fun findOnes(idChat: Int): Chat? = DatabaseSingleton.dbQuery {
        Chats.select {Chats.id eq idChat }.singleOrNull()?.resultRowToArticle()
    }
}