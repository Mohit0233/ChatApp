package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "media_streaming_sidecar")
data class MediaStreamingSidecar(//Unknown as to evidential data
    //provide a list of users and key message identifier
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo( name = "_id") val _id: Int,
    @ColumnInfo( name = "jid") val jid: Int,
    @ColumnInfo( name = "gjid") val gjid: Int,
    @ColumnInfo( name = "admin") val admin: Int,
    @ColumnInfo( name = "pending") val pending: Int,
    @ColumnInfo( name = "send_sender_key") val sendSenderKey: Int,
)
