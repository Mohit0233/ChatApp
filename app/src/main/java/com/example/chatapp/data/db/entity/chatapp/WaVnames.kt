package com.example.chatapp.data.db.entity.chatapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
@Entity(tableName = "wa_vnames")
data class WaVnames(
//maybe verified names
    @PrimaryKey
    @ColumnInfo(name = "_id") var _id: Int,
    @NotNull @ColumnInfo(name = "jid") var jid: String,
    @NotNull @ColumnInfo(name = "serial") var serial: Int,
    @NotNull @ColumnInfo(name = "issuer") var issuer: String,
    @ColumnInfo(name = "expires") var expires: Int,
    @NotNull @ColumnInfo(name = "verified_name") var verified_name: String,
    @ColumnInfo(name = "industry") var industry: String,
    @ColumnInfo(name = "city") var city: String,
    @ColumnInfo(name = "country") var country: String,
    @ColumnInfo(name = "verified_level") var verified_level: Int,
    @ColumnInfo(name = "identity_unconfirmed_since") var identity_unconfirmed_since: Int,
    @ColumnInfo(name = "cert_blob",typeAffinity = ColumnInfo.BLOB) var cert_blob: ByteArray,
    @ColumnInfo(name = "host_storage", defaultValue = "0") var host_storage: Int,
    @ColumnInfo(name = "actual_actors", defaultValue = "0") var actual_actors: Int,
    @ColumnInfo(name = "privacy_mode_ts", defaultValue = "0") var privacy_mode_ts: Int,
)