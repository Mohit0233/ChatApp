package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_ui_elements_reply")
data class MessageUiElementsReply(
    @PrimaryKey()@ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @ColumnInfo(name = "element_type") var element_type: Int,
    @ColumnInfo(name = "reply_values") var reply_values: String,
    @ColumnInfo(name = "reply_description") var reply_description: String
)