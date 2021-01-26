package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

//Contains information about events -- users
//joining, leaving, being kicked out of groups,
//users changing phone numbers, etc.
//○ indicated by the ‘status’ field.
@Entity(tableName = "group_participants_history")
data class GroupParticipantsHistory(//List of WhatsApp groups the user was previously a member of
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id") val _id: Int,
    @ColumnInfo(name = "timestamp") val timestamp: Long,
    @ColumnInfo(name = "gjid") val gjid: Int,
    @ColumnInfo(name = "jid") val jid: Int,
    @ColumnInfo(name = "action") val action: Int,
    @ColumnInfo(name = "old_phash") val oldPhash: Int,
    @ColumnInfo(name = "new_phash") val newPhash: Int
)
