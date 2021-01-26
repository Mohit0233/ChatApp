package com.example.chatapp.data.db.entity.chatapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wa_contact_storage_usage")
data class WaContactStorageUsage (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")var _id: Int,
    @ColumnInfo(name = "jid")var jid: String,
    @ColumnInfo(name = "conversation_size")var conversation_size: Int,
    @ColumnInfo(name = "conversation_message_count")var conversation_message_count: Int,
)