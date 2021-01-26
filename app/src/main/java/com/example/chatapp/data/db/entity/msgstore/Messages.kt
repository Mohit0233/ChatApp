package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

/*Saturday, June 16, 2018, at approximately 10:36:47 user 1585xxx6214-1528749173@g.us creates a group chat. On Saturday, June 16, 2018, at approximately 10:40 33 user 1585xxx0153@s.whatsapp.net responded “what’s up.” On Saturday, June 16, 2018, at approximately 10:41: 07 user 1585xxx2184@s.whatsapp.net responded “How are you doing bird?” On June 16, 2018, at approximately 10:41:44 user 1585xxx0077@s.whatsapp.net sent a message to the group “Hi Christopher.” On Saturday, June 16, at approximately 10:43:29 user 1585xxx0153@s.whatsapp.net responded “hi”, and the exchanged of messages continues between all the */

@Entity(tableName = "messages")
data class Messages(
//List of messages sent and received
// contains a record of every conversation that has been sent or received by the user
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id") val id: Long,
    // Individual – NUMBER@s.whatsapp.net
    // Group – CreatorNUMBER-GID@g.us – GID is timestamp in seconds (gid is the group creation time)
    @ColumnInfo(name = "key_remote_jid") val keyRemoteJId: String,
    //Message direction –
    // 0 is to me
    // 1 is from me
    @ColumnInfo(name = "key_from_me") val keyFromMe: Int,
    //Message ID
    @ColumnInfo(name = "key_id") val keyId: String,
    // The status column indicates message status values
    // (applicable if there atleast single contact in a group satisfies these)
    // 0 – received message
    // 13 – sent message seen (blue tick)
    // 5 – sent message unseen (double tick but not blue tick)
    // sent message to WhatApp server, but not received in recipient WhatsApp app(single tick)
    // sent/received calls
    // Message status: ‘0’=received,
    // ‘4’=waiting on the central server,
    // ‘5’=received by the destination,
    // ‘6’=control message
    // ‘13’=message opened by the recipient (read)
    @ColumnInfo(name = "status") val status: Int,
    // 1 when message has not yet sent
    // otherwise 0
    // ‘2’ if broadcast message,
    // ‘0’ otherwise
    @ColumnInfo(name = "needs_push") val needsPush: Int,
    //max length of the message 65536
    // Message content, when no attachment,
    // except when attachment type is a vCard, when it contains the actual vCard
    // Message content when media_wa_type =0
    @ColumnInfo(name = "data") val data: String,
    // Epoch time (milliseconds) of the time the message was sent
    // Time of send if key_from_me=’1’,
    // record insertion time otherwise (taken from the local device clock,
    // and encoded as a 13-digits millisecond Unix epoch time)
    @ColumnInfo(name = "timestamp") val timestamp: Long,
    //URL of attachment – Encoded for sent messages after Jan 2016,
    // and for received after March 2016 media_url gives location of the media in WhatsApp server
    // URL of the transmitted file (when media_type={‘1’,’2’,’3’})
    @ColumnInfo(name = "media_url") val mediaUrl: String,
    //MIME type of attachment, when attachment type is image, audio or video
    // MIME type of the transmitted file (when media_wa_type={‘1’,’2’,’3’}
    @ColumnInfo(name = "media_mime_type") val mediaMimeType: String,
    //Type of media in attachment:
    // 1=image,
    // 2=audio,
    // 3=video,
    // 4=vCard,
    // 5=location,
    // 8=audio/vido call,
    // 9=document,
    // 13=video also ?//Todo may be call check it
    // (0 = no attachment) „0‟ text
    @ColumnInfo(name = "media_wa_type") val mediaWaType: String,
    // Size of the attachment in bytes
    @ColumnInfo(name = "media_size") val mediaSize: Long,
    //The name of the attachment when it’s an image.
    // Preview of web-page when data contains a URL//Name of transmitted
    // file when (when media_wa_type={‘1’,’2’,’3’)
    @ColumnInfo(name = "media_name") val mediaName: String,
    // Text caption of the attachment.
    // The “title” of the web page when data contains a URL.
    @ColumnInfo(name = "media_caption") val mediaCaption: String,
    // Appears to be base 64 hash of the attachment when the attachment is an image, audio or a video
    // Based 64-encoded SHA-256 hash of the transmitted file when media_wa_type={‘1’,’2’,’3’
    @ColumnInfo(name = "media_hash") val mediaHash: String,
    // Duration of the attachment when the attachment is an audio, video or a call
    // Duration is sec. of the transmitted file when media_wa_type={‘1’,’2’,’3’})
    @ColumnInfo(name = "media_duration") val mediaDuration: Int,
    // Unknown, vast majority is 0,
    // ~ 10% is 1 and a very small number is 2
    @ColumnInfo(name = "origin") val origin: Int,
    // Latitude when the message attachment is a location
    // Latitude of the message sender (when media_wa_type=’5’)
    @ColumnInfo(name = "latitude") val latitude: Double,
    // Longitude when the message attachment is a location
    // Longitude of the message sender (when media_wa_type=’5’
    @ColumnInfo(name = "longitude") val longitude: Double,
    // Possible thumbnail of attached image – untried but this may decode
    // – http://stackoverflow.com/questions/24828383/how-to-read-the-thumb-image-column-in-the-whatsapp-messengers-sqlite3-database
    // Housekeeping information (no evidentiary value)
    @ColumnInfo(name = "thumb_image") val thumbImage: String,
    // Message sender (except self) in the case of groups (see key_remote_jid above)
    // ID of the sender only for group chat messages
    @ColumnInfo(name = "remote_resource") val remoteResource: String,
    // Epoch time (milliseconds) of the time the message was received
    // Time of receipt of the central server ack (taken from the local device clock, and encoded as a 13-digits millisecond Unix epoch time) if key_from_me=’1’ ‘-1’ otherwise
    @ColumnInfo(name = "received_timestamp") val receivedTimestamp: Long,
    // Unknown as yet, everything appears to be -1
    // Unused (always set to ‘-1’
    @ColumnInfo(name = "send_timestamp") val sendTimestamp: Long,
    // Epoch time (milliseconds) of the time the message was received by WhatsApp servers (single grey tick) (own messages only)
    @ColumnInfo(name = "receipt_server_timestamp") val receiptServerTimestamp: Long,
    // Epoch time (milliseconds) of the time the message was received by the recipient
    // (or all recipients in the case of a group) (double grey tick) (own messages only)
    // Time of receipt of the recipient ack (taken from the local device clock,
    // and encoded as a 13-digits millisecond Unix epoch time)
    // if key_from_me=’1’, ‘-1’ otherwise
    @ColumnInfo(name = "receipt_device_timestamp") val receiptDeviceTimestamp: Long,
    // Epoch time (milliseconds) of the time the message was read by the recipient
    // (or all recipients in the case of a group) (double blue tick) (own messages only)
    @ColumnInfo(name = "read_device_timestamp") val readDeviceTimestamp: Int,
    // NULL, of unknown evidential value
    @ColumnInfo(name = "played_device_timestamp") val playedDeviceTimestamp: Int,
    // NULL, of unknown evidential value
    // thumbnail of the transmitted file when media_wa_type={'1','3'}
    @ColumnInfo(name = "raw_data",typeAffinity = ColumnInfo.BLOB) val rawData: ByteArray,
    // Number of members in group when message was sent  recipient_count column
    // has 2 kind of values :
    // value 0 for individual chats or shows group-member count
    // if it is message in a group
    @ColumnInfo(name = "recipient_count") val recipientCount: Int,
    // Unknown
    @ColumnInfo(name = "participant_hash") val participantHash: String,
    // NULL, of unknown evidential value
    @ColumnInfo(name = "starred") val starred: Int,
    // row id (message_quotes table) of the message quoted
    @ColumnInfo(name = "quoted_row_id") val quotedRowId: Long,
    // NULL, of unknown evidential value
    @ColumnInfo(name = "mentioned_jids") val mentionedJIds: String,
    // NULL, of unknown evidential value
    @ColumnInfo(name = "multicast_id") val multicastId: String,
    // NULL – possibly reserved for the future when WhatsApp will allow you to edit sent messages (c.f. messages_edits table) – http://www.telegraph.co.uk/technology/2017/01/30/whatsapp-let-users-edit-recall-sent-messages/
    @ColumnInfo(name = "edit_version") val editVersion: Long,
    // NULL
    @ColumnInfo(name = "media_enc_hash") val mediaEncHash: String,
    @ColumnInfo(name = "payment_transaction_id") val paymentTransactionId: Long,
    @ColumnInfo(name = "forwarded") val forwarded: String,
    @ColumnInfo(name = "preview_type") val previewType:Int,
    @ColumnInfo(name = "send_count") val sendCount:Int,
    @ColumnInfo(name = "lookup_tables") val lookupTables:Int,

)

// INSERT INTO messages(_id, key_remote_jid, key_from_me, key_id, status, needs_push, data, timestamp, media_url, media_mime_type, media_wa_type, media_size, media_name, media_hash, media_duration, origin, latitude, longitude, thumb_image, received_timestamp, send_timestamp, receipt_server_timestamp, receipt_device_timestamp, read_device_timestamp, played_device_timestamp, mentioned_jids) VALUES (1, '-1', 0, '-1', -1, 0, NULL, 0, NULL, NULL, -1, -1, NULL, NULL, 0, 0, 0, 0, NULL, -1, -1, -1, -1, -1, -1, NULL)