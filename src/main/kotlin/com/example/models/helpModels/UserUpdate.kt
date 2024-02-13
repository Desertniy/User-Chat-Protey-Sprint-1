package com.example.models.helpModels

import kotlinx.serialization.Serializable

@Serializable
data class UserUpdate(val userid: Int, val login: String, val password: String)
