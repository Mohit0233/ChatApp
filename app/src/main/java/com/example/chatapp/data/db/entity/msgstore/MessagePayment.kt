package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_payment")
data class MessagePayment(
    @PrimaryKey()@ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @ColumnInfo(name = "sender_jid_row_id") var sender_jid_row_id: Int,
    @ColumnInfo(name = "receiver_jid_row_id") var receiver_jid_row_id: Int,
    @ColumnInfo(name = "amount_with_symbol") var amount_with_symbol: String,
    @ColumnInfo(name = "remote_resource") var remote_resource: String,
    @ColumnInfo(name = "remote_message_sender_jid_row_id") var remote_message_sender_jid_row_id: Int,
    @ColumnInfo(name = "remote_message_from_me") var remote_message_from_me: Int,
    @ColumnInfo(name = "remote_message_key") var remote_message_key: String
)