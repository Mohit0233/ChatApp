package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "media_refs")
data class MediaRefs(//References to media stored in the user’s filesystem
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id") val _id: Int,
    @ColumnInfo(name = "jid") val jid: Int,
    @ColumnInfo(name = "gjid") val gjid: Int
)
