package com.example.models.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class UserChatFindModel(val idChat: Int, val idUser: Int)
