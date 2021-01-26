package com.example.chatapp.data.db.entity.chatapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "wa_group_add_black_list")
class WaGroupAddBlackList (
    @PrimaryKey
    @NotNull @ColumnInfo(name = "jid")var jid: String
)