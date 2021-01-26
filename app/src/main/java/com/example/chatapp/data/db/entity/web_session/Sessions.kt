package com.example.chatapp.data.db.entity.web_session

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sessions")
class Sessions (
    @PrimaryKey
    @ColumnInfo(name="_id")var _id: Int,
    @ColumnInfo(name="browser_id")var browser_id: String,
    @ColumnInfo(name="secret")var secret: String,
    @ColumnInfo(name="token")var token: String,
    @ColumnInfo(name="os")var os: String,
    @ColumnInfo(name="browser_type")var browser_type: String,
    @ColumnInfo(name="login_time")var login_time: Int,
    @ColumnInfo(name="lat")var lat: Double,
    @ColumnInfo(name="accuracy")var accuracy: Double,
    @ColumnInfo(name="place_name")var place_name: String,
    @ColumnInfo(name="last_active")var last_active: Int,
    @ColumnInfo(name="timeout")var timeout: Boolean,
    @ColumnInfo(name="expiration")var expiration: Int,
    @ColumnInfo(name="last_push_sent")var last_push_sent: Int,
    @ColumnInfo(name="syncd_release")var syncd_release: Int,
)