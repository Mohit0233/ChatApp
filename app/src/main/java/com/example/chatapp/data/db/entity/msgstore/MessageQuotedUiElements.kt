package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "message_quoted_ui_elements")
data class MessageQuotedUiElements(
    @PrimaryKey()
    @NotNull @ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @ColumnInfo(name = "element_type") var element_type: Int,
    @ColumnInfo(name = "element_content") var element_content: String
)