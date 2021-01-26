package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_order")
data class MessageOrder (
    @PrimaryKey
    @ColumnInfo(name = "message_row_id")var message_row_id: Int,
    @ColumnInfo(name = "order_id")var order_id: String,
    @ColumnInfo(name = "thumbnail")var thumbnail: ByteArray,
    @ColumnInfo(name = "order_title")var order_title: String,
    @ColumnInfo(name = "item_count")var item_count: Int,
    @ColumnInfo(name = "status")var status: Int,
    @ColumnInfo(name = "surface")var surface: Int,
    @ColumnInfo(name = "message")var message: String,
    @ColumnInfo(name = "seller_jid")var seller_jid: Int,
    @ColumnInfo(name = "token")var token: String
)