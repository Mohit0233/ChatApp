package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages_dehydrated_hsm")
data class MessagesDehydratedHsm (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")var _id: Int,
    @ColumnInfo(name = "message_row_id")var message_row_id: Int,
    @ColumnInfo(name = "message_elementname")var message_elementname: Int,
    @ColumnInfo(name = "message_namespace")var message_namespace: Int,
    @ColumnInfo(name = "message_lg")var message_lg: Int,
)