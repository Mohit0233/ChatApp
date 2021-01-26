package com.example.chatapp.data.db.entity.chatapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "wa_group_admin_settings")
data class WaGroupAdminSettings(
    @PrimaryKey
    @NotNull @ColumnInfo(name = "jid") var jid: String,
    @NotNull @ColumnInfo(name = "restrict_mode") var restrict_mode: Boolean,
    @NotNull @ColumnInfo(name = "announcement_group") var announcement_group: Boolean,
    @NotNull @ColumnInfo(name = "no_frequently_forwarded") var no_frequently_forwarded: Boolean,
    @NotNull @ColumnInfo(name = "ephemeral_duration") var ephemeral_duration: Int,
    @ColumnInfo(name = "creator_jid") var creator_jid: String,
    @NotNull @ColumnInfo(name = "in_app_support") var in_app_support: Boolean
)