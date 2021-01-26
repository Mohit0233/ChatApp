package com.example.chatapp.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lables")
data class Lables (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id") var _id: Int,
    @ColumnInfo(name = "label_name") var label_name: String,
    @ColumnInfo(name = "predefined_id") var predefined_id: Int,
)