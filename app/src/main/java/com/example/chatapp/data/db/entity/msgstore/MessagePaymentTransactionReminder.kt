package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_payment_transaction_reminder")
data class MessagePaymentTransactionReminder(
    @PrimaryKey()@ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @ColumnInfo(name = "web_stub") var web_stub: String,
    @ColumnInfo(name = "amount") var amount: String,
    @ColumnInfo(name = "transfer_date") var transfer_date: String,
    @ColumnInfo(name = "payment_sender_name") var payment_sender_name: String,
    @ColumnInfo(name = "expiration") var expiration: Int,
    @ColumnInfo(name = "remote_message_key") var remote_message_key: String
)