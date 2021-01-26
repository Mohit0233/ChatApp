package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "messages_quotes")
data class MessagesQuote(//List of messages containing quotes of other messages


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id") val _id: Int,
    @ColumnInfo(name = "key_remote_jid") val keyRemoteJid: Int,
    @ColumnInfo(name = "key_from_me") val keyFromMe: Int,
    @ColumnInfo(name = "key_id") val keyId: Int,
    @ColumnInfo(name = "status") val status: Int,
    @ColumnInfo(name = "needs_push") val needsPush: Int,
    @ColumnInfo(name = "data") val data: Int,
    @ColumnInfo(name = "timestamp") val timestamp: Long,
    @ColumnInfo(name = "media_url") val mediaUrl: Int,
    @ColumnInfo(name = "media_mime_type") val mediaMimeType: Int,
    @ColumnInfo(name = "media_wa_type") val mediaWaType: Int,
    @ColumnInfo(name = "media_size") val mediaSize: Int,
    @ColumnInfo(name = "media_name") val mediaName: Int,
    @ColumnInfo(name = "media_caption") val mediaCaption: Int,
    @ColumnInfo(name = "media_hash") val mediaHash: Int,
    @ColumnInfo(name = "media_duration") val mediaDuration: Int,
    @ColumnInfo(name = "origin") val origin: Int,
    @ColumnInfo(name = "latitude") val latitude: Int,
    @ColumnInfo(name = "longitude") val longitude: Int,
    @ColumnInfo(name = "thumb_image") val thumbImage: Int,
    @ColumnInfo(name = "remote_resource") val remoteResource: Int,
    @ColumnInfo(name = "received_timestamp") val receivedTimestamp: Int,
    @ColumnInfo(name = "send_timestamp") val sendTimestamp: Int,
    @ColumnInfo(name = "receipt_server_timestamp") val receiptServerTimestamp: Int,
    @ColumnInfo(name = "receipt_device_timestamp") val receiptDeviceTimestamp: Int,
    @ColumnInfo(name = "read_device_timestamp") val readDeviceTimestamp: Int,
    @ColumnInfo(name = "played_device_timestamp") val playedDeviceTimestamp: Int,
    @ColumnInfo(name = "raw_data") val rawData: Int,
    @ColumnInfo(name = "recipient_count") val recipientCount: Int,
    @ColumnInfo(name = "participant_hash") val participantHash: Int,
    @ColumnInfo(name = "starred") val starred: Int,
    @ColumnInfo(name = "quoted_row_id") val quotedRowId: Int,
    @ColumnInfo(name = "mentioned_jids") val mentionedJids: Int,
    @ColumnInfo(name = "multicast_id") val multicastId: Int,
    @ColumnInfo(name = "edit_version") val editVersion: Int,
    @ColumnInfo(name = "media_enc_hash") val mediaEncHash: Int,
    @ColumnInfo(name = "payment_transaction_id") val paymentTransactionId: Int,
    @ColumnInfo(name = "forwarded") val forwarded: Int,
)
