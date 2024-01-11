package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class UserCredential(
    val login: String,
    val password: String
)