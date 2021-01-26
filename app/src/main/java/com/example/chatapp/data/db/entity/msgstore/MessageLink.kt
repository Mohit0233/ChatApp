package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_link")
data class MessageLink(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "_id") var _id: Int,
    @ColumnInfo(name = "chat_row_id") var chat_row_id: Int,
    @ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @ColumnInfo(name = "link_index") var link_index: Int
)