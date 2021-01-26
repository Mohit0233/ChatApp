package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_system_photo_change")
data class MessageSystemPhotoChange(
    @PrimaryKey()@ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @ColumnInfo(name = "new_photo_id") var new_photo_id: String,
    @ColumnInfo(name = "old_photo") var old_photo: ByteArray,
    @ColumnInfo(name = "new_photo") var new_photo: ByteArray
)