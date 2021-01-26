package com.example.chatapp.data.db.entity.chatapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wa_biz_profiles_websites")
data class WaBizProfilesWebsites (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")var _id: Int,
    @ColumnInfo(name = "wa_biz_profile_id")var wa_biz_profile_id: Int,
    @ColumnInfo(name = "websites")var websites: String,
)