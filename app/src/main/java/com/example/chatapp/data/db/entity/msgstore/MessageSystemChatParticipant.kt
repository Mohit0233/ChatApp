package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_system_chat_participant")
data class MessageSystemChatParticipant(
    @PrimaryKey
    @ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @ColumnInfo(name = "user_jid_row_id") var user_jid_row_id: Int
)