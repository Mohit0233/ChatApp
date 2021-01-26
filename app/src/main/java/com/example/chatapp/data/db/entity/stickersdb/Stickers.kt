package com.example.chatapp.data.db.entity.stickersdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "stickers")
data class Stickers(
    @PrimaryKey
    @NotNull @ColumnInfo(name = "plain_file_hash")var plainFileHash: String,
    @ColumnInfo(name = "encrypted_file_hash")var encrypted_file_hash: String,
    @ColumnInfo(name = "media_key")var media_key: String,
    @ColumnInfo(name = "mime_type")var mime_type: String,
    @NotNull @ColumnInfo(name = "height")var height: Int,
    @NotNull @ColumnInfo(name = "width")var width: Int,
    @ColumnInfo(name = "sticker_pack_id")var sticker_pack_id: String,
    @ColumnInfo(name = "file_path")var file_path: String,
    @ColumnInfo(name = "url")var url: String,
    @ColumnInfo(name = "file_size")var file_size: Int,
    @ColumnInfo(name = "direct_path")var direct_path: String,
)