package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "message_system_business_state")
data class MessageSystemBusinessState(
    @PrimaryKey()@ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @NotNull @ColumnInfo(name = "privacy_message_type") var privacy_message_type: Int,
    @ColumnInfo(name = "business_name") var business_name: String
)