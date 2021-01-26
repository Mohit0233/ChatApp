package com.example.chatapp.data.db.chatsettings

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class Settings (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")var _id: Int,
    @ColumnInfo(name = "jid")var jid: String,
    @ColumnInfo(name = "deleted")var deleted: Int,
    @ColumnInfo(name = "mute_end")var mute_end: Int,
    @ColumnInfo(name = "muted_notifications")var muted_notifications: Boolean,
    @ColumnInfo(name = "use_custom_notifications")var use_custom_notifications: Boolean,
    @ColumnInfo(name = "message_tone")var message_tone: String,
    @ColumnInfo(name = "message_vibrate")var message_vibrate: Int,
    @ColumnInfo(name = "message_popup")var message_popup: Int,
    @ColumnInfo(name = "message_light")var message_light: Int,
    @ColumnInfo(name = "call_tone")var call_tone: String,
    @ColumnInfo(name = "call_vibrate")var call_vibrate: Int,
    @ColumnInfo(name = "status_muted")var status_muted: Int,
    @ColumnInfo(name = "pinned")var pinned: Boolean,
    @ColumnInfo(name = "pinned_time")var pinned_time: Int,
    @ColumnInfo(name = "low_pri_notifications")var low_pri_notifications: Boolean,
    @ColumnInfo(name = "media_visibility")var media_visibility: Int,
)