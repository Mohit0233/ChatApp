package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_quoted_location")
data class MessageQuotedLocation (
    @PrimaryKey
    @ColumnInfo(name = "message_row_id")var message_row_id: Int,
    @ColumnInfo(name = "latitude")var latitude: Double,
    @ColumnInfo(name = "longitude")var longitude: Double,
    @ColumnInfo(name = "place_name")var place_name: String,
    @ColumnInfo(name = "place_address")var place_address: String,
    @ColumnInfo(name = "url")var url: String,
    @ColumnInfo(name = "thumbnail")var thumbnail: ByteArray
)