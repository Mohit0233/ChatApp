package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "frequents")
data class Frequents(//List of frequently contacted users, some users appear twice
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id") val _id: Int,
    @ColumnInfo(name = "jid") val jid: Int,
    @ColumnInfo(name = "type") val type: Int,
    @ColumnInfo(name = "message_count") val messageCount: Int
)
