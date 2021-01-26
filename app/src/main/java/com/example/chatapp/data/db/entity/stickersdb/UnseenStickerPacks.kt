package com.example.chatapp.data.db.entity.stickersdb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey

@Entity(tableName = "unseen_sticker_packs")
data class UnseenStickerPacks(
    @PrimaryKey
    @ColumnInfo(name = "pack_id")var pack_id: String
)
