package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "user_device")
data class UserDevice(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "_id") var _id: Int,
    @ColumnInfo(name = "user_jid_row_id") var user_jid_row_id: Int,
    @ColumnInfo(name = "device_jid_row_id") var device_jid_row_id: Int,
    @NotNull @ColumnInfo(name = "key_index",defaultValue = "0") var key_index: Int
)