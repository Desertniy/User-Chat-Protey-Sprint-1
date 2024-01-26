package com.example.dao

import com.example.models.Chats
import com.example.models.Messages
import com.example.models.Users
import com.example.models.Users_Chats
import io.ktor.server.config.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction


object DatabaseSingleton {
    fun init(config: ApplicationConfig) {
        val driverClassName = config.property("storage.driverClassName").getString()
        val jdbcURL = config.property("storage.jdbcURL").getString()
        val database = Database.connect(jdbcURL, driverClassName, user = "chatpguser", password = "1234")

        transaction(database) {
            SchemaUtils.create(Users, Chats, Messages, Users_Chats)
        }

    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}