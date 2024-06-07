package com.karna.mycards.presentation.util.proto

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val pin: Int = -1
)
