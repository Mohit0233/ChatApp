package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "labeled_messages")
data class LabeledMessages (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")var _idL: Int,
    @ColumnInfo(name = "label_id")var label_idL: Int,
    @ColumnInfo(name = "message_row_id")var message_row_idL: Int,
)