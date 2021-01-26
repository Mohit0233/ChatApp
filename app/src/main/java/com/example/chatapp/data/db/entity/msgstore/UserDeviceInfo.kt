package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "user_device_info")
data class UserDeviceInfo(
    @PrimaryKey()@ColumnInfo(name = "user_jid_row_id") var user_jid_row_id: Int,
    @NotNull @ColumnInfo(name = "raw_id") var raw_id: Int,
    @NotNull @ColumnInfo(name = "timestamp") var timestamp: Int
)