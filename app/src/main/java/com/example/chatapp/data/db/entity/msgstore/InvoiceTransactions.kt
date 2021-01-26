package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "invoice_transactions")
data class InvoiceTransactions(
    @PrimaryKey()@ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @ColumnInfo(name = "pay_transaction_id") var pay_transaction_id: Int
)