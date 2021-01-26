package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "quick_reply_attachments")
data class QuickReplyAttachments(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "_id") var _id: Int,
    @NotNull @ColumnInfo(name = "quick_reply_id") var quick_reply_id: String,
    @NotNull @ColumnInfo(name = "uri") var uri: String,
    @ColumnInfo(name = "caption") var caption: String,
    @ColumnInfo(name = "media_type") var media_type: Int
)