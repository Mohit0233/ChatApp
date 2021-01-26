package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_system_device_change")
data class MessageSystemDeviceChange(
    @PrimaryKey()@ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @ColumnInfo(name = "device_added_count") var device_added_count: Int,
    @ColumnInfo(name = "device_removed_count") var device_removed_count: Int
)