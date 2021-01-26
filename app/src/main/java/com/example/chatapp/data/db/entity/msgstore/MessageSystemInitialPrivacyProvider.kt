package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "message_system_initial_privacy_provider")
data class MessageSystemInitialPrivacyProvider(
    @PrimaryKey()@ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @NotNull @ColumnInfo(name = "privacy_provider",defaultValue = "0") var privacy_provider: Int,
    @ColumnInfo(name = "verified_biz_name") var verified_biz_name: String
)