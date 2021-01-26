package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mms_thumbnail_metadata")
data class MmsThumbnailMetadata(
    @PrimaryKey()@ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @ColumnInfo(name = "direct_path") var direct_path: String,
    @ColumnInfo(name = "media_key") var media_key: ByteArray,
    @ColumnInfo(name = "media_key_timestamp") var media_key_timestamp: Int,
    @ColumnInfo(name = "enc_thumb_hash") var enc_thumb_hash: String,
    @ColumnInfo(name = "thumb_hash") var thumb_hash: String,
    @ColumnInfo(name = "thumb_width") var thumb_width: Int,
    @ColumnInfo(name = "thumb_height") var thumb_height: Int,
    @ColumnInfo(name = "transferred") var transferred: Int,
    @ColumnInfo(name = "micro_thumbnail") var micro_thumbnail: ByteArray,
    @ColumnInfo(name = "insert_timestamp") var insert_timestamp: Int
)