package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_media")
class MessageMedia(
    @PrimaryKey
    @ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @ColumnInfo(name = "chat_row_id") var chat_row_id: Int,
    @ColumnInfo(name = "autotransfer_retry_enabled") var autotransfer_retry_enabled: Int,
    @ColumnInfo(name = "multicast_id") var multicast_id: String,
    @ColumnInfo(name = "media_job_uuid") var media_job_uuid: String,
    @ColumnInfo(name = "transferred") var transferred: Int,
    @ColumnInfo(name = "transcoded") var transcoded: Int,
    @ColumnInfo(name = "file_path") var file_path: String,
    @ColumnInfo(name = "file_size") var file_size: Int,
    @ColumnInfo(name = "suspicious_content") var suspicious_content: Int,
    @ColumnInfo(name = "trim_from") var trim_from: Int,
    @ColumnInfo(name = "trim_to") var trim_to: Int,
    @ColumnInfo(name = "face_x") var face_x: Int,
    @ColumnInfo(name = "face_y") var face_y: Int,
    @ColumnInfo(name = "media_key") var media_key: ByteArray,
    @ColumnInfo(name = "media_key_timestamp") var media_key_timestamp: Int,
    @ColumnInfo(name = "width") var width: Int,
    @ColumnInfo(name = "height") var height: Int,
    @ColumnInfo(name = "has_streaming_sidecar") var has_streaming_sidecar: Int,
    @ColumnInfo(name = "gif_attribution") var gif_attribution: Int,
    @ColumnInfo(name = "thumbnail_height_width_ratio") var thumbnail_height_width_ratio: Double,
    @ColumnInfo(name = "direct_path") var direct_path: String,
    @ColumnInfo(name = "first_scan_sidecar") var first_scan_sidecar: ByteArray,
    @ColumnInfo(name = "first_scan_length") var first_scan_length: Int,
    @ColumnInfo(name = "message_url") var message_url: String,
    @ColumnInfo(name = "mime_type") var mime_type: String,
    @ColumnInfo(name = "file_length") var file_length: Int,
    @ColumnInfo(name = "media_name") var media_name: String,
    @ColumnInfo(name = "file_hash") var file_hash: String,
    @ColumnInfo(name = "media_duration") var media_duration: Int,
    @ColumnInfo(name = "page_count") var page_count: Int,
    @ColumnInfo(name = "enc_file_hash") var enc_file_hash: String,
    @ColumnInfo(name = "partial_media_hash") var partial_media_hash: String,
    @ColumnInfo(name = "partial_media_enc_hash") var partial_media_enc_hash: String,
    @ColumnInfo(name = "is_animated_sticker") var is_animated_sticker: Int,
    @ColumnInfo(name = "original_file_hash") var original_file_hash: String,
    @ColumnInfo(name = "mute_video", defaultValue = "0") var mute_video: Int
)