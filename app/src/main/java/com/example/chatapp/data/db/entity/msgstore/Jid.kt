package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "jid")
data class Jid(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "_id") var _id: Int,
    @NotNull @ColumnInfo(name = "user") var user: String,
    @NotNull @ColumnInfo(name = "server") var server: String,
    @ColumnInfo(name = "agent") var agent: Int,
    @ColumnInfo(name = "device") var device: Int,
    @ColumnInfo(name = "type") var type: Int,
    @ColumnInfo(name = "raw_string") var raw_string: String
)