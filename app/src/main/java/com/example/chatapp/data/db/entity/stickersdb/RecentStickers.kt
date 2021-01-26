package com.example.chatapp.data.db.entity.stickersdb;

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "recent_stickers")

data class RecentStickers(
    @PrimaryKey
    @NotNull @ColumnInfo(name = "plaintext_hash")var plaintextHash: String,
    @NotNull @ColumnInfo(name = "entry_weight")var entry_weight: Double,
    @ColumnInfo(name = "hash_of_image_part")var hash_of_image_part: String,
    @ColumnInfo(name = "url")var url: String,
    @ColumnInfo(name = "enc_hash")var enc_hash: String,
    @ColumnInfo(name = "direct_path")var direct_path: String,
    @ColumnInfo(name = "mimetype")var mimetype: String,
    @ColumnInfo(name = "media_key")var media_key: String,
    @ColumnInfo(name = "file_size")var file_size: Int,
    @ColumnInfo(name = "width")var width: Int,
    @ColumnInfo(name = "height")var height: Int,
)