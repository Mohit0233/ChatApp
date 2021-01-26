package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "message_quote_invoice")
data class MessageQuoteInvoice(
    @PrimaryKey()@ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @NotNull @ColumnInfo(name = "amount") var amount: String,
    @NotNull @ColumnInfo(name = "note") var note: String,
    @ColumnInfo(name = "status") var status: Int,
    @ColumnInfo(name = "attachment_jpeg_thumbnail") var attachment_jpeg_thumbnail: ByteArray
)