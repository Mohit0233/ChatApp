package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "message_quoted_group_invite_legacy")
data class MessageQuotedGroupInviteLegacy(
    @PrimaryKey()@ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @NotNull @ColumnInfo(name = "group_jid_row_id") var group_jid_row_id: Int,
    @NotNull @ColumnInfo(name = "admin_jid_row_id") var admin_jid_row_id: Int,
    @ColumnInfo(name = "group_name") var group_name: String,
    @ColumnInfo(name = "invite_code") var invite_code: String,
    @ColumnInfo(name = "expiration") var expiration: Int,
    @ColumnInfo(name = "invite_time") var invite_time: Int,
    @ColumnInfo(name = "expired") var expired: Int
)