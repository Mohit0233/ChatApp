package com.example.chatapp.data.db.entity.media

import androidx.room.ColumnInfo

data class web_upload_media_data_store(
    @ColumnInfo(name = "media_id") var media_id: String,
    @ColumnInfo(name = "file_hash") var file_hash: String,
    @ColumnInfo(name = "media_key",typeAffinity = ColumnInfo.BLOB) var media_key: ByteArray,
    @ColumnInfo(name = "mime_type") var mime_type: String,
    @ColumnInfo(name = "upload_url") var upload_url: String,
    @ColumnInfo(name = "direct_path") var direct_path: String,
    @ColumnInfo(name = "enc_file_hash") var enc_file_hash: String,
    @ColumnInfo(name = "file_size") var file_size: Int,
    @ColumnInfo(name = "width") var width: Int,
    @ColumnInfo(name = "height") var height: Int,
)