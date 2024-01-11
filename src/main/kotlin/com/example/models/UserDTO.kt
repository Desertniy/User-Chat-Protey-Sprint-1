package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(val username: String,
                   val password: String)