package com.example.chatapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.chatapp.data.db.entity.msgstore.MessagesLinks
@Dao
interface MessagesLinkDao {


    @Insert
    suspend fun addMessagesLink(messagesLinks: MessagesLinks)

    @Query( "SELECT * FROM messages_links ORDER BY _id")
    fun readAllMessagesLink(): LiveData<List<MessagesLinks>>
}