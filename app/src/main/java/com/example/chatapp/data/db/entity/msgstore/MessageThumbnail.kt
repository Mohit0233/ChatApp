package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_thumbnail")
data class MessageThumbnail(
    @PrimaryKey()@ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @ColumnInfo(name = "thumbnail") var thumbnail: ByteArray
)