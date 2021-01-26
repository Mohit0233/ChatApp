package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages_links")
data class MessagesLinks(//List of messages containing URL links


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id") val _id: Int,
    @ColumnInfo(name = "key_remote_jid") val keyRemoteJid: Int,
    @ColumnInfo(name = "message_row_id") val messageRowId: Int,
    @ColumnInfo(name = "link_index") val linkIndex: Int
)
