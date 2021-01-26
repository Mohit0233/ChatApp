package com.example.chatapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.chatapp.data.db.entity.msgstore.MessagesVcardsJid
@Dao
interface MessagesVcardsJidDao {
    @Insert
    suspend fun addMessagesVcardsJid(messagesVcardsJid: MessagesVcardsJid)

    @Query( "SELECT * FROM messages_vcards_jids ORDER BY _id")
    fun readAllMessagesVcardsJid(): LiveData<List<MessagesVcardsJid>>
}