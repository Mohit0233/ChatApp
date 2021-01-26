package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_thumbnails")
data class MessageThumbnails(//Storage location of media thumbnails since April 2017
    // provide the whatsapp id of the communication partner
    @ColumnInfo(name = "thumbnail") val thumbnail: ByteArray,//Blob – the first 4 bytes are “ff d8 ff e0” i.e. the file signature hash of a jpg
    @ColumnInfo(name = "timestamp") val timestamp: Long,//Date time in epoch
    @PrimaryKey
    @ColumnInfo(name = "key_remote_jid") val keyRemoteJid: Int,//Sender / Receiver ID
    @ColumnInfo(name = "key_from_me") val keyFromMe: Int,//Same as key_from_me in messages table – 0 is to me, 1 is from me
    @ColumnInfo(name = "key_id") val keyId: Int,//30 char hex string

)
