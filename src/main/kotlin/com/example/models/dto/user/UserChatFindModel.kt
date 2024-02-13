package com.example.models.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class UserChatFindModel(val id_chat: Int, val id_user: Int)
