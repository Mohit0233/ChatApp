package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "receipt_device")
data class ReceiptDevice(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "_id") var _id: Int,
    @NotNull @ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @NotNull @ColumnInfo(name = "receipt_device_jid_row_id") var receipt_device_jid_row_id: Int,
    @ColumnInfo(name = "receipt_device_timestamp") var receipt_device_timestamp: Int
)