package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_mentions")
data class MessageMentions(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "_id") var _id: Int,
    @ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @ColumnInfo(name = "jid_row_id") var jid_row_id: Int
)