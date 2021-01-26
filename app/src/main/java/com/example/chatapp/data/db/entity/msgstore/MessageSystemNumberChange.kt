package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_system_number_change")
data class MessageSystemNumberChange(
    @PrimaryKey()@ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @ColumnInfo(name = "old_jid_row_id") var old_jid_row_id: Int,
    @ColumnInfo(name = "new_jid_row_id") var new_jid_row_id: Int
)