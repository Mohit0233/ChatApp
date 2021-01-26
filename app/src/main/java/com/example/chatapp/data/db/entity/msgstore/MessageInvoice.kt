package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "message_invoice")
data class MessageInvoice(
    @PrimaryKey
    @ColumnInfo(name = "message_row_id") var message_row_id: Int,
    @NotNull @ColumnInfo(name = "wa_invoice_id") var wa_invoice_id: String,
    @NotNull @ColumnInfo(name = "amount") var amount: String,
    @NotNull @ColumnInfo(name = "note") var note: String,
    @ColumnInfo(name = "token") var token: String,
    @ColumnInfo(name = "sender_jid_row_id") var sender_jid_row_id: Int,
    @ColumnInfo(name = "receiver_jid_row_id") var receiver_jid_row_id: Int,
    @ColumnInfo(name = "status") var status: Int,
    @ColumnInfo(name = "status_ts") var status_ts: Int,
    @ColumnInfo(name = "creation_ts") var creation_ts: Int,
    @ColumnInfo(name = "attachment_type") var attachment_type: Int,
    @ColumnInfo(name = "attachment_mimetype") var attachment_mimetype: String,
    @ColumnInfo(name = "attachment_media_key") var attachment_media_key: ByteArray,
    @ColumnInfo(name = "attachment_media_key_ts") var attachment_media_key_ts: Int,
    @ColumnInfo(name = "attachment_file_sha256") var attachment_file_sha256: ByteArray,
    @ColumnInfo(name = "attachment_file_enc_sha256") var attachment_file_enc_sha256: ByteArray,
    @ColumnInfo(name = "attachment_direct_path") var attachment_direct_path: String,
    @ColumnInfo(name = "attachment_jpeg_thumbnail") var attachment_jpeg_thumbnail: ByteArray,
    @ColumnInfo(name = "metadata") var metadata: String
)