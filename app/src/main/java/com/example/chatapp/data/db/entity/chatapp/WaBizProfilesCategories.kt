package com.example.chatapp.data.db.entity.chatapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "wa_biz_profiles_categories")
class WaBizProfilesCategories(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")var _id: Int,
    @NotNull @ColumnInfo(name = "wa_biz_profile_id")var wa_biz_profile_id: Int,
    @NotNull @ColumnInfo(name = "category_id")var category_id: String,
    @NotNull @ColumnInfo(name = "category_name")var category_name: String
)