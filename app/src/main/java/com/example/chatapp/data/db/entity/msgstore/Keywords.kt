package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "keywords",indices = [Index(value = ["keyword"], unique = true)])
data class Keywords(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "_id") var _id: Int,
    @NotNull @ColumnInfo(name = "keyword") var keyword: String
)