package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "quick_reply_keywords")
data class QuickReplyKeywords(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "_id") var _id: Int,
    @NotNull @ColumnInfo(name = "quick_reply_id") var quick_reply_id: String,
    @NotNull @ColumnInfo(name = "keyword_id") var keyword_id: String
)