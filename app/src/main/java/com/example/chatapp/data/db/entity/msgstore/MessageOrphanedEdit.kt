package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "message_orphaned_edit")
data class MessageOrphanedEdit (
    @PrimaryKey
    @ColumnInfo(name = "_id")var _id: Int,
    @NotNull @ColumnInfo(name = "key_id")var key_id: String,
    @NotNull @ColumnInfo(name = "from_me")var from_me: Int,
    @NotNull @ColumnInfo(name = "chat_row_id")var chat_row_id: Int,
    @NotNull @ColumnInfo(name = "sender_jid_row_id",defaultValue = "0")var sender_jid_row_id: Int ,
    @ColumnInfo(name = "timestamp")var timestamp: Int,
    @NotNull @ColumnInfo(name = "message_type")var message_type: Int ,
    @ColumnInfo(name = "revoked_key_id")var revoked_key_id: String,
    @ColumnInfo(name = "retry_count")var retry_count: Int
)