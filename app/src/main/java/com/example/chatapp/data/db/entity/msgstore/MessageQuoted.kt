package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "message_quoted")
data class MessageQuoted(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @NotNull @ColumnInfo(name = "chat_row_id") var chat_row_id: Int,
    @NotNull @ColumnInfo(name = "parent_message_chat_row_id") var parent_message_chat_row_id: Int,
    @NotNull @ColumnInfo(name = "from_me") var from_me: Int,
    @ColumnInfo(name = "sender_jid_row_id") var sender_jid_row_id: Int,
    @NotNull @ColumnInfo(name = "key_id") var key_id: String,
    @ColumnInfo(name = "timestamp") var timestamp: Int,
    @ColumnInfo(name = "message_type") var message_type: Int,
    @ColumnInfo(name = "origin") var origin: Int,
    @ColumnInfo(name = "text_data") var text_data: String,
    @ColumnInfo(name = "payment_transaction_id") var payment_transaction_id: String,
    @ColumnInfo(name = "lookup_tables") var lookup_tables: Int
)