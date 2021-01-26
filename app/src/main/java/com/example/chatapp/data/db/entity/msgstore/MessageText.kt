package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_text")
data class MessageText(
    @PrimaryKey()@ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "page_title") var page_title: String,
    @ColumnInfo(name = "url") var url: String,
    @ColumnInfo(name = "font_style") var font_style: Int,
    @ColumnInfo(name = "text_color") var text_color: Int,
    @ColumnInfo(name = "background_color") var background_color: Int,
    @ColumnInfo(name = "preview_type") var preview_type: Int
)