package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_future")
data class MessageFuture(
    @PrimaryKey()@ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @ColumnInfo(name = "version") var version: Int,
    @ColumnInfo(name = "data") var data: ByteArray
)