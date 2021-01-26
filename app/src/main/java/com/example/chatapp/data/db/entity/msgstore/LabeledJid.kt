package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "labeled_jid")
data class LabeledJid(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "_id") var _id: Int,
    @NotNull @ColumnInfo(name = "label_id") var label_id: Int,
    @NotNull @ColumnInfo(name = "jid_row_id") var jid_row_id: Int
)