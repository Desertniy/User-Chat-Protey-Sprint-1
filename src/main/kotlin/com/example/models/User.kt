package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class User(val id_user: Int, val username: String, val password: String)
