package com.example.chatapp.data.db.entity.stickersdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "new_sticker_packs")
data class NewStickerPacks(
    @PrimaryKey
    @ColumnInfo(name = "pack_id")var pack_id: String,
)