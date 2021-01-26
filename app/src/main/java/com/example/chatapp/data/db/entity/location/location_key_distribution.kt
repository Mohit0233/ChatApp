package com.example.chatapp.data.db.entity.location

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

class location_key_distribution (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")var _id: Int,
    @NotNull @ColumnInfo(name = "jid")var jid: String,
    @NotNull @ColumnInfo(name = "sent_to_server")var sent_to_server: Boolean
)