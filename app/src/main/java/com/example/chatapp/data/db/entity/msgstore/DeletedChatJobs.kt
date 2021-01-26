package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "deleted_chat_jobs")
data class DeletedChatJobs(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id") var _id: Int,
    @ColumnInfo(name = "chat_row_id") var chat_row_id: Int,
    @ColumnInfo(name = "block_size") var block_size: Int,
    @ColumnInfo(name = "deleted_message_row_id") var deleted_message_row_id: Int,
    @ColumnInfo(name = "deleted_starred_message_row_id") var deleted_starred_message_row_id: Int,
    @ColumnInfo(name = "deleted_messages_remove_files") var deleted_messages_remove_files: Boolean,
    @ColumnInfo(name = "deleted_categories_message_row_id") var deleted_categories_message_row_id: Int,
    @ColumnInfo(name = "deleted_categories_starred_message_row_id") var deleted_categories_starred_message_row_id: Int,
    @ColumnInfo(name = "deleted_categories_remove_files") var deleted_categories_remove_files: Boolean,
    @ColumnInfo(name = "deleted_message_categories") var deleted_message_categories: String
)