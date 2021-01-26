package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import androidx.room.Entity

@Entity(tableName = "message")
data class Message(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")var _id: Int,
    @NotNull @ColumnInfo(name = "chat_row_id")var chat_row_id: Int,
    @NotNull @ColumnInfo(name = "from_me")var from_me: Int,
    @NotNull @ColumnInfo(name = "key_id")var key_id: String,
    @ColumnInfo(name = "sender_jid_row_id")var sender_jid_row_id: Int,
    @ColumnInfo(name = "status")var status: Int,
    @ColumnInfo(name = "broadcast")var broadcast: Int,
    @ColumnInfo(name = "recipient_count")var recipient_count: Int,
    @ColumnInfo(name = "participant_hash")var participant_hash: String,
    @ColumnInfo(name = "origination_flags")var origination_flags: Int,
    @ColumnInfo(name = "origin")var origin: Int,
    @ColumnInfo(name = "timestamp")var timestamp: Int,
    @ColumnInfo(name = "received_timestamp")var received_timestamp: Int,
    @ColumnInfo(name = "receipt_server_timestamp")var receipt_server_timestamp: Int,
    @ColumnInfo(name = "message_type")var message_type: Int,
    @ColumnInfo(name = "text_data")var text_data: String,
    @ColumnInfo(name = "starred")var starred: Int,
    @ColumnInfo(name = "lookup_tables")var lookup_tables: Int,
    @NotNull @ColumnInfo(name = "sort_id",defaultValue = "0")var sort_id: Int
)