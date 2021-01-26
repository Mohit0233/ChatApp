package com.example.chatapp.data.db.entity.stickersdb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "installed_sticker_packs")
data class InstalledStickerPacks(
        @PrimaryKey
        @NotNull @ColumnInfo(name = "installed_id")var installedId: String,
        @ColumnInfo(name = "installed_name")var installed_name: String,
        @ColumnInfo(name = "installed_publisher")var installed_publisher: String,
        @ColumnInfo(name = "installed_description")var installed_description: String,
        @NotNull @ColumnInfo(name = "installed_size")var installed_size: Int,
        @NotNull @ColumnInfo(name = "installed_image_data_hash")var installed_image_data_hash: String,
        @NotNull @ColumnInfo(name = "installed_tray_image_id")var installed_tray_image_id: String,
        @ColumnInfo(name = "installed_tray_image_preview_id")var installed_tray_image_preview_id: String,
        @NotNull @ColumnInfo(name = "installed_animated_pack")var installed_animated_pack: Int,
)