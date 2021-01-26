package com.example.chatapp.data.db.entity.location

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

class location_sharer (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")var _id: Int,
    @NotNull @ColumnInfo(name = "remote_jid")var remote_jid: String,
    @NotNull @ColumnInfo(name = "from_me")var from_me: Boolean,
    @NotNull @ColumnInfo(name = "remote_resource")var remote_resource: String,
    @NotNull @ColumnInfo(name = "expires")var expires: Int,
    @NotNull @ColumnInfo(name = "message_id")var message_id: String
)