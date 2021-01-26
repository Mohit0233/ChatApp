package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_location")
data class MessageLocation(
    @PrimaryKey
    @ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @ColumnInfo(name = "chat_row_id") var chat_row_id: Int,
    @ColumnInfo(name = "latitude") var latitude: Double,
    @ColumnInfo(name = "longitude") var longitude: Double,
    @ColumnInfo(name = "place_name") var place_name: String,
    @ColumnInfo(name = "place_address") var place_address: String,
    @ColumnInfo(name = "url") var url: String,
    @ColumnInfo(name = "live_location_share_duration") var live_location_share_duration: Int,
    @ColumnInfo(name = "live_location_sequence_number") var live_location_sequence_number: Int,
    @ColumnInfo(name = "live_location_final_latitude") var live_location_final_latitude: Double,
    @ColumnInfo(name = "live_location_final_longitude") var live_location_final_longitude: Double,
    @ColumnInfo(name = "live_location_final_timestamp") var live_location_final_timestamp: Int,
    @ColumnInfo(name = "map_download_status") var map_download_status: Int
)