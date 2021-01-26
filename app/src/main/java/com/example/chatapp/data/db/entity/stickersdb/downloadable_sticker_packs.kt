package com.example.chatapp.data.db.entity.stickersdb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "downloadable_sticker_packs")
data class downloadable_sticker_packs(
        @PrimaryKey
        @NotNull @ColumnInfo(name = "id")var id: String,
        @ColumnInfo(name = "name")var name: String,
        @ColumnInfo(name = "publisher")var publisher: String,
        @ColumnInfo(name = "description")var description: String,
        @NotNull @ColumnInfo(name = "size")var size: Int,
        @ColumnInfo(name = "tray_image_id")var tray_image_id: String,
        @ColumnInfo(name = "tray_image_preview_id")var tray_image_preview_id: String,
        @ColumnInfo(name = "preview_image_id_array")var preview_image_id_array: String,
        @NotNull @ColumnInfo(name = "image_data_hash")var image_data_hash: String,
        @NotNull @ColumnInfo(name = "animated_pack")var animated_pack: Int,
)