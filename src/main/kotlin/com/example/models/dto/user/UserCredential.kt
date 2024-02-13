package com.example.models.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class UserCredential(
    val login: String,
    val password: String
)