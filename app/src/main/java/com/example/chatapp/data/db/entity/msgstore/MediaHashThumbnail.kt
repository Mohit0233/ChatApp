package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "media_hash_thumbnail")
data class MediaHashThumbnail(
    @PrimaryKey
    @ColumnInfo(name = "media_hash") var media_hash: String,
    @ColumnInfo(name = "thumbnail") var thumbnail: ByteArray
)