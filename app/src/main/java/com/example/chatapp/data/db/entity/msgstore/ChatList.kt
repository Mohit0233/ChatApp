package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Contains the list of groups joined, and their names
@Entity(tableName = "chat_list")
data class ChatList(
    //List of conversations //: chat_list – Contains all contacts (subject column is empty) and groups (has value in subject column) where chat has been done (both sent & receive).
//contain a record of each conversation head  by the user
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id") val _id: Int,
    @ColumnInfo(name = "keyRemoteJId") val keyRemoteJId: Int,//PHONENUMBER@s.whatsapp.net and PHONENUMBER-TIMESTAMP@g.us Conversation ID
    @ColumnInfo(name = "message_table_jid") val messageTableJid: Int, //References _id column in messages table – points to last message received in conversation  message_table_id column field values maps to docid column values in the messages_fts_content table (contains all messages), which says last message (sent/received).
    @ColumnInfo(name = "subject") val subject: Int,//Group Only – Name of the Group
    @ColumnInfo(name = "creation") val creation: Int,//Group Only – Creation Time (milliseconds since epoch) of the Group Group has column creation showing creation date
    @ColumnInfo(name = "last_read_message_table_id") val lastReadMessageTableId: Int,//Same as message_table_jid – points to last read message in conversation
    @ColumnInfo(name = "last_read_receipt_sent_message_table_id") val lastReadReceiptSentMessageTableId: Int,//Same as message_table_jid – points to last read message in conversation where a read receipt has been sent
    @ColumnInfo(name = "archived") val archived: Int,//Archived bit – https://www.whatsapp.com/faq/en/bb/20888029 – 1 if archived, NULL otherwise
    @ColumnInfo(name = "sort_timestamp") val sortTimestamp: Int,//An epoch timestamp in milliseconds, used for sorting when group or chat was created
    @ColumnInfo(name = "mod_tag") val modTag: Int,//Unknown
    @ColumnInfo(name = "gen") val gen: Int,//Unknown – all NULL
    @ColumnInfo(name = "my_messages") val myMessages: Int,//Signifies whether you have sent a message in this conversation -1 if so, NULL if not
    @ColumnInfo(name = "plaintext_disabled") val plaintextDisabled: Int,//Unknown – all 1
    @ColumnInfo(name = "last_message_table_id") val lastMessageTableId: Int,//Appears to be the same as message_table_jid
    @ColumnInfo(name = "unseen_message_count") val unseenMessageCount: Int,//Count of unseen messages in conversation
    @ColumnInfo(name = "unseen_missed_calls_count") val unseenMissedCallsCount: Int,//Count of unseen voice calls in conversation
    @ColumnInfo(name = "unseen_row_count") val unseenRowCount: Int,//Unknown, not the same as unseen_message_count
    @ColumnInfo(name = "vcard_ui_dismissed") val vcardUiDismissed: Int,//Possibly whether the vCard UI has been dismissed or not.
    @ColumnInfo(name = "change_number_notified_message_id") val change_number_notified_message_id: Int,
    @ColumnInfo(name = "last_important_message_table_id") val last_important_message_table_id: Int,
    @ColumnInfo(name = "show_group_description") val showGroupDescription: Int,
    @ColumnInfo(name = "ephemeral_expiration") val ephemeral_expiration: Int,
    @ColumnInfo(name = "last_read_ephemeral_message_table_id") val last_read_ephemeral_message_table_id: Int,
    @ColumnInfo(name = "ephemeral_setting_timestamp") val ephemeral_setting_timestamp: Int,
)

