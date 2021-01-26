package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "message_privacy_state")
data class MessagePrivacyState(
    @PrimaryKey()
    @NotNull @ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @ColumnInfo(name = "host_storage") var host_storage: Int,
    @ColumnInfo(name = "actual_actors") var actual_actors: Int,
    @NotNull @ColumnInfo(name = "privacy_mode_ts") var privacy_mode_ts: Int,
    @ColumnInfo(name = "business_name") var business_name: String
)