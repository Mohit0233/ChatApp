package com.example.chatapp.data.local.model

import android.location.Location
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "messages")
data class Message(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: Int,
    @ColumnInfo(name = "key_remote_jid")
    val keyRemoteJId: Int,
    @ColumnInfo(name = "key_from_me")
    val keyFromMe: Int,
    @ColumnInfo(name = "key_id")
    val keyId: Int,
    @ColumnInfo(name = "status")
    val status: String,
    @ColumnInfo(name = "needs_push")
    val needsPush: Boolean,
    @ColumnInfo(name = "data")
    val data: String,//65,536
    @ColumnInfo(name = "timestamp")
    val timestamp: Timestamp,
    @ColumnInfo(name = "media_url")
    val mediaUrl: String,
    @ColumnInfo(name = "media_mime_type")
    val mediaMimeType: Int,
    @ColumnInfo(name = "media_wa_type")
    val mediaWaType: Int,
    @ColumnInfo(name = "media_size")
    val mediaSize: Int,
    @ColumnInfo(name = "media_name")
    val mediaName: String,
    @ColumnInfo(name = "latitude")
    val latitude: Location,
    @ColumnInfo(name = "longitude")
    val longitude: String,
    @ColumnInfo(name = "thumb_image")
    val thumbImage: String,
    @ColumnInfo(name = "remote_resource")
    val remoteResource: String,
    @ColumnInfo(name = "received_time_stamp")
    val receivedTimestamp: Timestamp,
    @ColumnInfo(name = "send_timestamp")
    val sendTimestamp: Timestamp,
    @ColumnInfo(name = "receipt_server_timestamp")
    val receiptServerTimestamp: Timestamp,
    @ColumnInfo(name = "receipt_device_timestamp")
    val receiptDeviceTimestamp: Timestamp,
)
