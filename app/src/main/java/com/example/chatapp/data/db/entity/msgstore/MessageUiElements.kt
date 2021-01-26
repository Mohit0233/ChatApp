package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "message_ui_elements")
data class MessageUiElements(
    @PrimaryKey(autoGenerate = true)@NotNull @ColumnInfo(name = "_id") var _id: Int,
    @NotNull @ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @ColumnInfo(name = "element_type") var element_type: Int,
    @ColumnInfo(name = "element_content") var element_content: String
)