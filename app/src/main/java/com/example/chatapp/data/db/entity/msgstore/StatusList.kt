package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "status_list")
data class StatusList(//Relating to WhatsApp Status, where broadcast messages may be sent to all contacts, and disappear after 24 hours
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id") val _id: Int,
    @ColumnInfo(name = "key_remote_jid") val keyRoteJid: Int,
    @ColumnInfo(name = "message_table_id") val messageTableId: Int,
    @ColumnInfo(name = "last_read_message_table_id") val lastReadMessageTableId: Int,
    @ColumnInfo(name = "last_read_receipt_sent_message_table_id") val lastReadReceiptSentMessageTableId: Int,
    @ColumnInfo(name = "first_unread_message_table_id") val firstUnreadMessageTableId: Int,
    @ColumnInfo(name = "autodownload_limit_message_table_id") val autodownloadLimitMessageTableId: Int,
    @ColumnInfo(name = "timestamp") val timestamp: Int,
    @ColumnInfo(name = "unseen_count") val unseenCount: Int,
    @ColumnInfo(name = "total_count") val totalCount: Int
)
