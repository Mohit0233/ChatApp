package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "status",indices = [Index(value = ["jid_row_id"], unique = true)])
data class Status(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "_id") var _id: Int,
    @ColumnInfo(name = "jid_row_id") var jid_row_id: Int,
    @ColumnInfo(name = "message_table_id") var message_table_id: Int,
    @ColumnInfo(name = "last_read_message_table_id") var last_read_message_table_id: Int,
    @ColumnInfo(name = "last_read_receipt_sent_message_table_id") var last_read_receipt_sent_message_table_id: Int,
    @ColumnInfo(name = "first_unread_message_table_id") var first_unread_message_table_id: Int,
    @ColumnInfo(name = "autodownload_limit_message_table_id") var autodownload_limit_message_table_id: Int,
    @ColumnInfo(name = "timestamp") var timestamp: Int,
    @ColumnInfo(name = "unseen_count") var unseen_count: Int,
    @ColumnInfo(name = "total_count") var total_count: Int
)