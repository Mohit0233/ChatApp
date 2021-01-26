package com.example.chatapp.data.db.entity.stickersdb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "sticker_pack_order")
data class StickerPackOrder(
        @PrimaryKey
    @NotNull @ColumnInfo(name = "sticker_pack_id")var sticker_pack_id: String,
        @NotNull @ColumnInfo(name = "pack_order")var pack_order: Int
)
