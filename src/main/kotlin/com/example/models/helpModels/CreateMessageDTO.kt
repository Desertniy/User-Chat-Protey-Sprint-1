package com.example.models.helpModels

import kotlinx.serialization.Serializable

@Serializable
data class CreateMessageDTO(val id_user: Int, val id_chat: Int, val text_message: String)