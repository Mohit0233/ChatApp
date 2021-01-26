package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "receipt_orphaned")
data class ReceiptOrphaned(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "_id") var _id: Int,
    @NotNull @ColumnInfo(name = "chat_row_id") var chat_row_id: Int,
    @NotNull @ColumnInfo(name = "from_me") var from_me: Int,
    @NotNull @ColumnInfo(name = "key_id") var key_id: String,
    @NotNull @ColumnInfo(name = "receipt_device_jid_row_id") var receipt_device_jid_row_id: Int,
    @ColumnInfo(name = "receipt_recipient_jid_row_id") var receipt_recipient_jid_row_id: Int,
    @ColumnInfo(name = "status") var status: Int,
    @ColumnInfo(name = "timestamp") var timestamp: Int
)