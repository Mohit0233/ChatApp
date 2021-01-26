package com.example.chatapp.data.db.entity.chatapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "wa_biz_profiles")
class WaBizProfiles(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")var _id: Int ,
    @NotNull @ColumnInfo(name = "jid")var jid: String ,
    @ColumnInfo(name = "email")var email: String,
    @ColumnInfo(name = "address")var address: String,
    @ColumnInfo(name = "business_description")var business_description: String,
    @ColumnInfo(name = "latitude")var latitude: Double,
    @ColumnInfo(name = "longitude")var longitude: Double,
    @ColumnInfo(name = "tag")var tag: String,
    @ColumnInfo(name = "vertical")var vertical: String,
    @ColumnInfo(name = "time_zone")var time_zone: String,
    @ColumnInfo(name = "hours_note")var hours_note: String,
    @ColumnInfo(name = "has_catalog",defaultValue = "0")var has_catalog: Boolean,
    @ColumnInfo(name = "address_postal_code")var address_postal_code: String,
    @ColumnInfo(name = "address_city_id")var address_city_id: String,
    @ColumnInfo(name = "address_city_name")var address_city_name: String,
    @ColumnInfo(name = "commerce_experience")var commerce_experience: String,
    @ColumnInfo(name = "shop_url")var shop_url: String,
    @ColumnInfo(name = "cart_enabled", defaultValue = "0")var cart_enabled: Boolean
)