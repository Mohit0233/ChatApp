package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages_vcards")
data class MessagesVcard(//Empty table, possibly for future use
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id") val _id: Int,
    @ColumnInfo(name = "message_row_id") val messageRowId: Int,
    @ColumnInfo(name = "sender_jid") val senderJid: Int,
    @ColumnInfo(name = "chat_jid") val chatJid: Int,
    @ColumnInfo(name = "vcard") val vcard: Int,
)
