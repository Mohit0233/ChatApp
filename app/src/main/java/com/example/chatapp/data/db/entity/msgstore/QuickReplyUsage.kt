package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "quick_reply_usage")
data class QuickReplyUsage(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "_id") var _id: Int,
    @NotNull @ColumnInfo(name = "quick_reply_id") var quick_reply_id: String,
    @ColumnInfo(name = "usage_date") var usage_date: Long,//Date
    @ColumnInfo(name = "usage_count") var usage_count: Int
)