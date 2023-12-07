package com.example.repository

import com.example.dao.DatabaseSingleton.dbQuery
import com.example.models.User
import com.example.models.Users
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll


class UserRepo {
    private fun resultRowToArticle(row: ResultRow) = User(
        id_user = row[Users.id],
        username = row[Users.username],
        password = row[Users.password],
    )

    /*Возвращает все объекты из таблицы*/
    suspend fun findAll(): List<User> = dbQuery {
        TODO()
    }

    /*Запись в таблицу*/
    suspend fun create(username: String, password: String): User = dbQuery {
        TODO()
    }

    /*Обновление записи*/
    suspend fun update(id: Int, username: String, password: String): User = dbQuery {
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