package com.example.chatapp.data.db.entity.chatapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wa_contacts")
data class WaContact(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id") val _id: Int,//Sequence number of the record (set by SQLite)
    @ColumnInfo(name = "jid") val jid: Int,//WhatsApp ID of the contact (a string structured as ’x@s.whatsapp.net’, where ’x’ is the phone number of the
    @ColumnInfo(name = "is_whatsapp_user") val isWhatsappUser: Int,//Todo("Replace Whatsapp") //contains ’1’ if the contact corresponds to an actual WhatsApp user, ’0’ otherwise
    @ColumnInfo(name = "status") val status: Int,//Text in the status line of the contact//Status line of the contact (as set in his/her prole)
    @ColumnInfo(name = "status_timestamp") val statusTimestamp: Int,//Contains a timestamp in the Unix Epoch Time (ms) format
    @ColumnInfo(name = "number") val number: Int, //Todo("Replace with email")//Phone number associated with the contact //Phone number associated to the contact
    @ColumnInfo(name = "raw_contact_id") val rawContactId: Int,//Sequence number of the contact //Sequence number of the contact
    @ColumnInfo(name = "display_name") val displayName: Int,//Display name of the contact //Display name of the contact
    @ColumnInfo(name = "phone_type") val phoneType: Int,//Type of the phone //Type of phone
    @ColumnInfo(name = "phone_label") val phoneLabel: Int,//Label associated with the phone number //Label associated to the phone number
    @ColumnInfo(name = "unseen_msg_count") val unseenMsgCount: Int,//number of messages sent by this contact that have been received, but still have to be read
    @ColumnInfo(name = "photo_ts",typeAffinity = ColumnInfo.BLOB) val photoTs: ByteArray, //Contains a timestamp in the Unix Epoch Time format //Unknown, always set to ‘0’
    @ColumnInfo(name = "thumb_ts") val thumbTs: Int,//Contains a timestamp in the Unix Epoch Time format //Unix epoch time (10 digits) indicating when the contact has set his/her current avatar pictures
    @ColumnInfo(name = "photo_id_timestamp") val photoIdTimestamp: Int,//Contains a timestamp in the Unix Epoch Time (ms) format //Unix million second epoch time (13 digits) indicating when the current avatar picture of the contact has been downloaded locally
    @ColumnInfo(name = "given_name") val givenName: Int,//The field value is the same as in display_name for each contact//Given name of the user
    @ColumnInfo(name = "wa_name") val waName: Int,//WhatsApp name of the contact (as set in their profile)//WhatsApp name of the contact (as set in his/her prole)
    @ColumnInfo(name = "sort_name") val sortName: Int,//Name of the contact used in sorting operations //Name of the contact user in sorting operation
    @ColumnInfo(name = "nickname") val nickname: Int,//WhatsApp nickname of the contact (as set in their profile)
    @ColumnInfo(name = "company") val company: Int,//Company (as set in the contact’s profile)
    @ColumnInfo(name = "title") val title: Int,//Title (Mr/Ms/Mrs	as set in the contact's profile)
    @ColumnInfo(name = "status_autodownload_disabled") val status_autodownload_disabled: Int,
    @ColumnInfo(name = "keep_timestamp") val keep_timestamp: Int,
    @ColumnInfo(name = "is_spam_reported") val is_spam_reported: Int,
    @ColumnInfo(name = "is_sidelist_synced",defaultValue = "0") val is_sidelist_synced: Boolean,
    @ColumnInfo(name = "is_business_synced",defaultValue = "0") val is_business_synced: Boolean,
//family_name name of the family
)