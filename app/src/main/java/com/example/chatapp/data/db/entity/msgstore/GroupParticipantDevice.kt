package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "group_participant_device")
data class GroupParticipantDevice(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "_id") var _id: Int,
    @NotNull @ColumnInfo(name = "group_participant_row_id") var group_participant_row_id: Int,
    @NotNull @ColumnInfo(name = "device_jid_row_id") var device_jid_row_id: Int,
    @ColumnInfo(name = "sent_sender_key") var sent_sender_key: Int
)