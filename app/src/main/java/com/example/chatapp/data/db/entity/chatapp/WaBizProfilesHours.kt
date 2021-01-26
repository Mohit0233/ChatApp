package com.example.chatapp.data.db.entity.chatapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "wa_biz_profiles_hours")
data class WaBizProfilesHours(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")var _id: Int,
    @NotNull @ColumnInfo(name = "wa_biz_profile_id")var wa_biz_profile_id: Int,
    @ColumnInfo(name = "day_of_week")var day_of_week: String,
    @ColumnInfo(name = "mode")var mode: String,
    @ColumnInfo(name = "open_time")var open_time: Int,
    @ColumnInfo(name = "close_time")var close_time: Int
)