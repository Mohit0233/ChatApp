package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "chat",indices = [Index(value = ["jid_row_id"], unique = true)])
data class Chat(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "_id") var _id: Int,
    @ColumnInfo(name = "jid_row_id") var jid_row_id: Int,
    @ColumnInfo(name = "hidden") var hidden: Int,
    @ColumnInfo(name = "subject") var subject: String,
    @ColumnInfo(name = "created_timestamp") var created_timestamp: Int,
    @ColumnInfo(name = "display_message_row_id") var display_message_row_id: Int,
    @ColumnInfo(name = "last_message_row_id") var last_message_row_id: Int,
    @ColumnInfo(name = "last_read_message_row_id") var last_read_message_row_id: Int,
    @ColumnInfo(name = "last_read_receipt_sent_message_row_id") var last_read_receipt_sent_message_row_id: Int,
    @ColumnInfo(name = "last_important_message_row_id") var last_important_message_row_id: Int,
    @ColumnInfo(name = "archived") var archived: Int,
    @ColumnInfo(name = "sort_timestamp") var sort_timestamp: Int,
    @ColumnInfo(name = "mod_tag") var mod_tag: Int,
    @ColumnInfo(name = "gen") var gen: Double,
    @ColumnInfo(name = "spam_detection") var spam_detection: Int,
    @ColumnInfo(name = "unseen_earliest_message_received_time") var unseen_earliest_message_received_time: Int,
    @ColumnInfo(name = "unseen_message_count") var unseen_message_count: Int,
    @ColumnInfo(name = "unseen_missed_calls_count") var unseen_missed_calls_count: Int,
    @ColumnInfo(name = "unseen_row_count") var unseen_row_count: Int,
    @ColumnInfo(name = "plaintext_disabled") var plaintext_disabled: Int,
    @ColumnInfo(name = "vcard_ui_dismissed") var vcard_ui_dismissed: Int,
    @ColumnInfo(name = "change_number_notified_message_row_id") var change_number_notified_message_row_id: Int,
    @ColumnInfo(name = "show_group_description") var show_group_description: Int,
    @ColumnInfo(name = "ephemeral_expiration") var ephemeral_expiration: Int,
    @ColumnInfo(name = "last_read_ephemeral_message_row_id") var last_read_ephemeral_message_row_id: Int,
    @ColumnInfo(name = "ephemeral_setting_timestamp") var ephemeral_setting_timestamp: Int
)