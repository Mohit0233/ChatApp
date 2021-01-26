package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conversion_tuples")
data class ConversionTuples (
    @PrimaryKey
    @ColumnInfo(name = "key_remote_jid") var key_remote_jid:Int,
    @ColumnInfo(name = "data") var data:String,
    @ColumnInfo(name = "source") var source:String,
    @ColumnInfo(name = "last_interaction") var last_interaction: String,
    @ColumnInfo(name = "first_interaction") var first_interaction: String,
    )