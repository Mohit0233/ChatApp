package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "receipt_user")
data class ReceiptUser(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "_id") var _id: Int,
    @NotNull @ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @NotNull @ColumnInfo(name = "receipt_user_jid_row_id") var receipt_user_jid_row_id: Int,
    @ColumnInfo(name = "receipt_timestamp") var receipt_timestamp: Int,
    @ColumnInfo(name = "read_timestamp") var read_timestamp: Int,
    @ColumnInfo(name = "played_timestamp") var played_timestamp: Int
)