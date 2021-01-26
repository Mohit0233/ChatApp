package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_payment_status_update")
data class MessagePaymentStatusUpdate(
    @PrimaryKey()@ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @ColumnInfo(name = "transaction_info") var transaction_info: String,
    @ColumnInfo(name = "transaction_data") var transaction_data: String,
    @ColumnInfo(name = "init_timestamp") var init_timestamp: String,
    @ColumnInfo(name = "update_timestamp") var update_timestamp: String,
    @ColumnInfo(name = "amount_data") var amount_data: String
)