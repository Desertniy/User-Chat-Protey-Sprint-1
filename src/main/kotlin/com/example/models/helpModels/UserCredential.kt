package com.example.models.helpModels

import kotlinx.serialization.Serializable

@Serializable
data class UserCredential(
    val login: String,
    val password: String
)