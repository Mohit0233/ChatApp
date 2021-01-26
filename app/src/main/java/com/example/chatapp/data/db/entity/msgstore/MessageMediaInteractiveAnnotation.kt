package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_media_interactive_annotation")
class MessageMediaInteractiveAnnotation(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id") var _id: Int,
    @ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @ColumnInfo(name = "location_latitude") var location_latitude: Double,
    @ColumnInfo(name = "location_longitude") var location_longitude: Double,
    @ColumnInfo(name = "location_name") var location_name: String,
    @ColumnInfo(name = "sort_order") var sort_order: Int
)