package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "missed_call_logs")
data class MissedCallLogs(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "_id") var _id: Int,
    @ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @ColumnInfo(name = "timestamp") var timestamp: Int,
    @ColumnInfo(name = "video_call") var video_call: Int,
    @NotNull @ColumnInfo(name = "group_jid_row_id",defaultValue = "0") var group_jid_row_id: Int,
    @ColumnInfo(name = "is_joinable_group_call") var is_joinable_group_call: Int
)