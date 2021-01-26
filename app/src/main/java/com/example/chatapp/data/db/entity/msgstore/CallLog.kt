package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "call_log")
data class CallLog(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id") var _id: Int,
    @ColumnInfo(name = "jid_row_id") var jid_row_id: Int,
    @ColumnInfo(name = "from_me") var from_me: Int,
    @ColumnInfo(name = "call_id") var call_id: String,
    @ColumnInfo(name = "transaction_id") var transaction_id: Int,
    @ColumnInfo(name = "timestamp") var timestamp: Int,
    @ColumnInfo(name = "video_call") var video_call: Int,
    @ColumnInfo(name = "duration") var duration: Int,
    @ColumnInfo(name = "call_result") var call_result: Int,
    @ColumnInfo(name = "bytes_transferred") var bytes_transferred: Int,
    @ColumnInfo(name = "group_jid_row_id",defaultValue = "0") var group_jid_row_id: Int,
    @ColumnInfo(name = "is_joinable_group_call") var is_joinable_group_call: Int,
    @NotNull @ColumnInfo(name = "call_creator_device_jid_row_id",defaultValue = "0") var call_creator_device_jid_row_id: Int,
    @ColumnInfo(name = "call_random_id") var call_random_id: String
)