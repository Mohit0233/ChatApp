package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "call_log_participant_v2")
data class CallLogParticipantV2(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "_id") var _id: Int,
    @ColumnInfo(name = "call_log_row_id") var call_log_row_id: Int,
    @ColumnInfo(name = "jid_row_id") var jid_row_id: Int,
    @ColumnInfo(name = "call_result") var call_result: Int
)