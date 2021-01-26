package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_quoted_media")
data class MessageQuotedMedia(
    @PrimaryKey()@ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @ColumnInfo(name = "media_job_uuid") var media_job_uuid: String,
    @ColumnInfo(name = "transferred") var transferred: Int,
    @ColumnInfo(name = "file_path") var file_path: String,
    @ColumnInfo(name = "file_size") var file_size: Int,
    @ColumnInfo(name = "media_key") var media_key: ByteArray,
    @ColumnInfo(name = "media_key_timestamp") var media_key_timestamp: Int,
    @ColumnInfo(name = "width") var width: Int,
    @ColumnInfo(name = "height") var height: Int,
    @ColumnInfo(name = "direct_path") var direct_path: String,
    @ColumnInfo(name = "message_url") var message_url: String,
    @ColumnInfo(name = "mime_type") var mime_type: String,
    @ColumnInfo(name = "file_length") var file_length: Int,
    @ColumnInfo(name = "media_name") var media_name: String,
    @ColumnInfo(name = "file_hash") var file_hash: String,
    @ColumnInfo(name = "media_duration") var media_duration: Int,
    @ColumnInfo(name = "page_count") var page_count: Int,
    @ColumnInfo(name = "enc_file_hash") var enc_file_hash: String,
    @ColumnInfo(name = "thumbnail") var thumbnail: ByteArray
)


