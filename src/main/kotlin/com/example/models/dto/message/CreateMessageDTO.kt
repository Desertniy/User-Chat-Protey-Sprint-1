package com.example.models.dto.message

import kotlinx.serialization.Serializable

@Serializable
data class CreateMessageDTO(val idUser: Int, val idChat: Int, val textMessage: String)