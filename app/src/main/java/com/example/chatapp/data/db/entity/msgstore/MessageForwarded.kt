package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_forwarded")
data class MessageForwarded(
    @PrimaryKey
    @ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @ColumnInfo(name = "forward_score") var forward_score: Int
)