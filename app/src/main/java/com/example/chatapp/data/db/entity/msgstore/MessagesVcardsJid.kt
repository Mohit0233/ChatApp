package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages_vcards_jids")
data class MessagesVcardsJid(//Empty table, possibly for future use
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")val _id: Int,
    @ColumnInfo(name = "message_row_id")val messageRowId: Int,
    @ColumnInfo(name = "vcard_jid")val vcardJid: Int,
    @ColumnInfo(name = "vcard_row_id")val vcardRowId: Int,
)
