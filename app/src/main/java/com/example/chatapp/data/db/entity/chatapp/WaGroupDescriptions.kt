package com.example.chatapp.data.db.entity.chatapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "wa_group_descriptions")
data class WaGroupDescriptions(
    @PrimaryKey
    @NotNull @ColumnInfo(name = "jid")var jid: String,
    @NotNull @ColumnInfo(name = "description")var description: String,
    @ColumnInfo(name = "description_id")var description_id: Int,
    @ColumnInfo(name = "description_time")var description_time: Int,
    @NotNull @ColumnInfo(name = "description_setter_jid")var description_setter_jid: String,
    @NotNull @ColumnInfo(name = "description_id_string")var description_id_string: String
)
