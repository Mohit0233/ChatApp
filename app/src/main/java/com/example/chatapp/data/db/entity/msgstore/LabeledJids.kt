package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "labeled_jids")
data class LabeledJids (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id") var _id: Int,
    @ColumnInfo(name = "label_id") var label_id: Int,
    @ColumnInfo(name = "jid") var jid: Int,
)