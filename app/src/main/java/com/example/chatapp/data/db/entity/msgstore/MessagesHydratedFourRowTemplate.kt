package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages_hydrated_four_row_template")
data class MessagesHydratedFourRowTemplate (
    @PrimaryKey
    @ColumnInfo(name = "message_row_id")var message_row_id: Int,
    @ColumnInfo(name = "message_template_id")var message_template_id: String
)