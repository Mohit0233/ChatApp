package com.example.chatapp.data.db.entity.stickersdb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "third_party_whitelist_packs",primaryKeys = ["authority","sticker_pack_id"])
data class ThirdPartyWhitelistPacks(
        @NotNull @ColumnInfo(name = "authority")var authority: String,
        @NotNull @ColumnInfo(name = "sticker_pack_id")var sticker_pack_id: String,
        @ColumnInfo(name = "sticker_pack_name")var sticker_pack_name: String,
        @ColumnInfo(name = "sticker_pack_publisher")var sticker_pack_publisher: String,
        @ColumnInfo(name = "sticker_pack_image_data_hash")var sticker_pack_image_data_hash: String,
        @ColumnInfo(name = "avoid_cache")var avoid_cache: Int,
        @ColumnInfo(name = "is_animated_pack")var is_animated_pack: Int,
)