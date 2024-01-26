package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(val login: String,
                   val username: String,
                   val password: String)