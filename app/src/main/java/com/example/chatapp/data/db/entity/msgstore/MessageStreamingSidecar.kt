package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_streaming_sidecar")
data class MessageStreamingSidecar(
    @PrimaryKey()@ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @ColumnInfo(name = "sidecar") var sidecar: ByteArray,
    @ColumnInfo(name = "chunk_lengths") var chunk_lengths: ByteArray,
    @ColumnInfo(name = "timestamp") var timestamp: Int
)