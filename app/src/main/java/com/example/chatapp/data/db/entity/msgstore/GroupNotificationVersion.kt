package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "group_notification_version")
data class GroupNotificationVersion(
    @PrimaryKey()@ColumnInfo(name = "group_jid_row_id") var group_jid_row_id: Int,
    @NotNull @ColumnInfo(name = "subject_timestamp") var subject_timestamp: Int,
    @NotNull @ColumnInfo(name = "announcement_version") var announcement_version: Int,
    @NotNull @ColumnInfo(name = "participant_version") var participant_version: Int
)