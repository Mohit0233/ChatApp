package com.example.chatapp.data.db.entity.msgstore

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Contains the list of participants in a group, along
//with their admin status.
@Entity(tableName = "group_participants")
data class GroupParticipants(//List of WhatsApp groups, along with their participants
    // provide the whatsapp id of the contact
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id") val _id: Int,
    @ColumnInfo(name = "gjid") val gjid: Int,//gjid column contains group id
    @ColumnInfo(name = "jid") val jid: Int,// jid column contains group participants id (creator does not has this field
    @ColumnInfo(name = "admin") val admin: Int,// admin column has values 0 or 1: Value 0 – user Value 1 – admin
    @ColumnInfo(name = "pending") val pending: Int,
    @ColumnInfo(name = "send_sender_key") val sendSenderKey: Int
)