package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="receipts")
data class Receipts(//Listing of message receipts for messages sent to groups
//contains a receipt of all the messages sent and received.
// With a unique message identifier, and
// the WhatsApp ID of the communicating partner.
// As well as the date and time sent and received

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id") val _id: Int,
    @ColumnInfo(name = "key_remote_jid") val keyRemoteJid: Int,
    @ColumnInfo(name = "key_id") val keyId: Int,
    @ColumnInfo(name = "remote_resource") val remoteResource: Int,
    @ColumnInfo(name = "receipt_device_timestamp") val receiptDeviceTimestamp: Int,
    @ColumnInfo(name = "read_device_timestamp") val readDeviceTimestamp: Int,
    @ColumnInfo(name = "played_device_timestamp") val playedDeviceTimestamp: Int,
)
