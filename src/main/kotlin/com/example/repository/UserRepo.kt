package com.example.repository

import com.example.dao.DatabaseSingleton.dbQuery
import com.example.models.User
import com.example.models.dto.user.UserCredential
import com.example.models.Users
import com.example.models.dto.user.UserUpdate
import com.example.utils.encodeToBase64
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq


class UserRepo {
    private fun ResultRow.resultRowToArticle(): User {
        return User(
            id_user = this[Users.id].value,
            login = this[Users.login],
            username = this[Users.username]?: "Uknown",
            password = this[Users.password],
        )
    }

    /*Запись в таблицу*/
    suspend fun create(user: UserCredential): User = dbQuery {
        val userId = Users.insertAndGetId {
            it[password] = user.password
            it[login] = user.login
        }
        Users.select { Users.id eq userId }.single().resultRowToArticle()

    }

    /*Обновление записи*/
    suspend fun update(user: UserUpdate): User? = dbQuery {
        val res = Users.update({ Users.id eq user.userid }){
            it[login] = user.login
            it[password] = user.password
        }
        if (res > 0)
            Users.select { Users.id eq user.userid }.single().resultRowToArticle()
        else
            null
    }

    /*Удаление записи*/
    suspend fun delete(userId: Int): Boolean = dbQuery {
        val res = Users.deleteWhere { Users.id eq userId }
        res > 0
    }

    /*Поиск одной записи*/
    suspend fun findOnes(userId: Int): User? = dbQuery {
        val result = Users.select { Users.id eq userId}.singleOrNull()
        result?.resultRowToArticle()
    }

    suspend fun findAllUsers(): List<User> = dbQuery{
        Users.selectAll().map { row ->
            User(
                id_user = row[Users.id].value,
                login = row[Users.login],
                username = row[Users.username]?: "Uknown",
                password = row[Users.password],
            )
        }
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

    suspend fun deleteToken(userId: Int): Boolean = dbQuery {
        val res = Users.update({Users.id eq userId}) {
            it[token] = null
        }
        res > 0
    }

}