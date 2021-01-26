package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "message_system")
data class MessageSystem(
    @PrimaryKey()@ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @NotNull @ColumnInfo(name = "action_type") var action_type: Int
)