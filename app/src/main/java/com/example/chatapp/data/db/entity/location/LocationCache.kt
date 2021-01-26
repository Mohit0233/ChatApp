package com.example.chatapp.data.db.entity.location

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "location_cache")
class LocationCache(
    @PrimaryKey(autoGenerate = true)
    @NotNull @ColumnInfo(name = "_id") var _id: Int,
    @NotNull @ColumnInfo(name = "jid") var jid: String,
    @NotNull @ColumnInfo(name = "latitude") var latitude: Double,
    @NotNull @ColumnInfo(name = "longitude") var longitude: Double,
    @NotNull @ColumnInfo(name = "accuracy") var accuracy: Int,
    @NotNull @ColumnInfo(name = "speed") var speed: Double,
    @NotNull @ColumnInfo(name = "bearing") var bearing: Int,
    @NotNull @ColumnInfo(name = "location_ts") var location_ts: Int,
)