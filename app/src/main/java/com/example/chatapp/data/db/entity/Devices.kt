package com.example.chatapp.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

//companion_devices
@Entity(tableName = "devices")
data class Devices (
    @ColumnInfo(name = "_id") var _id :Int,
    @ColumnInfo(name = "device_id") var device_id :String,
    @ColumnInfo(name = "device_os") var device_os :String,
    @ColumnInfo(name = "platform_type") var platform_type :Int,
    @ColumnInfo(name = "last_active") var last_active :Int,
    @ColumnInfo(name = "login_time") var login_time :Int,
    @ColumnInfo(name = "logout_time") var logout_time :Int,
    @ColumnInfo(name = "adv_key_index") var adv_key_index :Int,
    @ColumnInfo(name = "full_sync_required") var full_sync_required :Int,
    @ColumnInfo(name = "place_name") var place_name :String,
    )