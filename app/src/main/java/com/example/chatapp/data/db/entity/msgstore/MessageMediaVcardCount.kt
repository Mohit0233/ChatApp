package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_media_vcard_count")
data class MessageMediaVcardCount(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "_id") var _id: Int,
    @ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @ColumnInfo(name = "count") var count: Int
)