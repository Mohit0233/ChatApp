package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "frequent")
data class Frequent(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "_id") var _id: Int,
    @NotNull @ColumnInfo(name = "jid_row_id") var jid_row_id: Int,
    @NotNull @ColumnInfo(name = "type") var type: Int,
    @NotNull @ColumnInfo(name = "message_count") var message_count: Int
)