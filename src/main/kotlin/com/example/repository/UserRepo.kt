package com.example.repository

import com.example.dao.DatabaseSingleton.dbQuery
import com.example.models.User
import com.example.models.UserCredential
import com.example.models.UserDTO
import com.example.models.Users
import com.example.plugins.encodeToBase64
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq


class UserRepo {
    private fun ResultRow.resultRowToArticle(): User {
        return User(
            id_user = this[Users.id].value,
            login = this[Users.login],
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
            it[login] = user.login
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
    suspend fun findOnes(user: UserCredential): User? = dbQuery {
        val result = Users.select { (Users.login eq user.login) and (Users.password eq user.password) }.singleOrNull()
        result?.resultRowToArticle()
    }

    suspend fun updateToken(user: UserCredential): String? = dbQuery {
        val token = encodeToBase64(user.login + ":" + user.password)
        val res = Users.update({ Users.login eq user.login }){
            it[Users.token] = token
        }
        if (res > 0)
            token
        else
            null

    }

    suspend fun findToken(token: String): User? = dbQuery{
        val res = Users.select{(Users.token eq token)}.singleOrNull()
        res?.resultRowToArticle()
    }

    suspend fun deleteToken(login: String): Boolean = dbQuery {
        val res = Users.update({Users.login eq login}) {
            it[Users.token] = null
        }
        res > 0
    }

}