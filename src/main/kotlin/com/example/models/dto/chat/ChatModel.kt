package com.example.models.dto.chat

import kotlinx.serialization.Serializable

@Serializable
data class ChatModel(val idCreator: Int, val nameChat: String)
