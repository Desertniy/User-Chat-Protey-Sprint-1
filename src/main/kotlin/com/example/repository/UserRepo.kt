package com.example.repository

import com.example.dao.DatabaseSingleton.dbQuery
import com.example.models.User
import com.example.models.UserDTO
import com.example.models.Users
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll


class UserRepo {
    private fun ResultRow.resultRowToArticle(): User {
        return User(
            id_user = this[Users.id].value,
            username = this[Users.username],
            password = this[Users.password],
        )
    }

    /*Возвращает все объекты из таблицы*/
    suspend fun findAll(): List<User> = dbQuery {
        TODO()
    }

    /*Запись в таблицу*/
    suspend fun create(user: UserDTO): User = dbQuery {
        val userId = Users.insertAndGetId {
            it[username] = user.username
            it[password] = user.password
        }
        Users.select { Users.id eq userId }.single().resultRowToArticle()

    }

    /*Обновление записи*/
    suspend fun update(user: User): User = dbQuery {
        TODO()
    }

    /*Удаление записи*/
    suspend fun delete(id: Int): Boolean = dbQuery {
        TODO()
    }

    /*Поиск одной записи*/
    suspend fun findOnes(id: Int): User = dbQuery {
        TODO()
    }

}