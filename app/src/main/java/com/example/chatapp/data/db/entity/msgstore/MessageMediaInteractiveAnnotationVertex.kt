package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "message_media_interactive_annotation_vertex")
data class MessageMediaInteractiveAnnotationVertex(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id") var _id: Int ,
    @ColumnInfo(name = "message_media_interactive_annotation_row_id") var message_media_interactive_annotation_row_id: Int,
    @ColumnInfo(name = "x") var x: Double,
    @ColumnInfo(name = "y") var y: Double,
    @ColumnInfo(name = "sort_order") var sort_order: Int
)