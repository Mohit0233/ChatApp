package com.example.chatapp.data.local.model

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wa_contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: Int,
    @ColumnInfo(name = "jid")
    val jid: Int,
    @ColumnInfo(name = "is_whatsapp_user")
    val isWhatsappUser: Int,//Todo("Replace Whatsapp")
    @ColumnInfo(name = "is_iphone")
    val isiPhone: Int,
    @ColumnInfo(name = "status")
    val status: Int,
    @ColumnInfo(name = "number")
    val number: Int, //Todo("Replace with email")
    @ColumnInfo(name = "raw_contact_id")
    val rawContactId: Int,
    @ColumnInfo(name = "display_name")
    val displayName: Int,
    @ColumnInfo(name = "phone_type")
    val phoneType: Int,
    @ColumnInfo(name = "phone_label")
    val phoneLabel: Int,
    @ColumnInfo(name = "unseen_msg_count")
    val unseenMsgCount: Int,
    @ColumnInfo(name = "photo_ts")
    val photoTS: Bitmap
)
