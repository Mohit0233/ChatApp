package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "group_participant_user")
data class GroupParticipantUser(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "_id") var _id: Int,
    @NotNull @ColumnInfo(name = "group_jid_row_id") var group_jid_row_id: Int,
    @NotNull @ColumnInfo(name = "user_jid_row_id") var user_jid_row_id: Int,
    @ColumnInfo(name = "rank") var rank: Int,
    @ColumnInfo(name = "pending") var pending: Int
)