package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_external_ad_content")
data class MessageExternalAdContent(
    @PrimaryKey()@ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "body") var body: String,
    @ColumnInfo(name = "media_type") var media_type: Int,
    @ColumnInfo(name = "thumbnail_url") var thumbnail_url: String,
    @ColumnInfo(name = "full_thumbnail") var full_thumbnail: ByteArray,
    @ColumnInfo(name = "micro_thumbnail") var micro_thumbnail: ByteArray,
    @ColumnInfo(name = "media_url") var media_url: String,
    @ColumnInfo(name = "source_type") var source_type: String,
    @ColumnInfo(name = "source_id") var source_id: String,
    @ColumnInfo(name = "source_url") var source_url: String
)