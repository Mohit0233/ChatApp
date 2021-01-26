package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "message_template_quoted")
data class MessageTemplateQuoted (
    @PrimaryKey
    @ColumnInfo(name = "message_row_id")var message_row_id: Int,
    @NotNull @ColumnInfo(name = "content_text_data")var content_text_data: String,
    @ColumnInfo(name = "footer_text_data")var footer_text_data: String
        )