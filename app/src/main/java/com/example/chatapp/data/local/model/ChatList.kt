package com.example.chatapp.data.local.model

import androidx.room.Entity

@Entity(tableName = "chat_list")
data class ChatList(
    val id: Int,
    val keyRemoteJId: Int,
    val messageTableId: Int
)

