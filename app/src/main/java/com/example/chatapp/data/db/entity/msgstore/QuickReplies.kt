package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "quick_replies",indices = [Index(value = ["title"], unique = true)])
data class QuickReplies(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "_id") var _id: Int,
    @NotNull @ColumnInfo(name = "title") var title: String,
    @NotNull @ColumnInfo(name = "content") var content: String
)