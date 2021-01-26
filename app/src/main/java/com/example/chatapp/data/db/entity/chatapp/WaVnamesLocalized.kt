package com.example.chatapp.data.db.entity.chatapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "wa_vnames_localized")
data class WaVnamesLocalized (
    @PrimaryKey
    @ColumnInfo(name = "_id")var _id: Int,
    @NotNull @ColumnInfo(name = "jid")var jid: String ,
    @NotNull @ColumnInfo(name = "lg")var lg: String ,
    @NotNull @ColumnInfo(name = "lc")var lc: String ,
    @NotNull @ColumnInfo(name = "verified_name")var verified_name: String ,
)