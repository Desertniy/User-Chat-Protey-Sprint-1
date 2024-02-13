package com.example.models.helpModels

import kotlinx.serialization.Serializable

@Serializable
data class ChatModel(val idCreator: Int, val nameChat: String)
