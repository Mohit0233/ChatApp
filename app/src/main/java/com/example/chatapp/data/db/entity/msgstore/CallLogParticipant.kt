package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "call_log_participant")
data class CallLogParticipant(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "_id") var _id: Int,
    @ColumnInfo(name = "call_logs_row_id") var call_logs_row_id: Int,
    @ColumnInfo(name = "jid") var jid: String,
    @ColumnInfo(name = "call_result") var call_result: Int
)