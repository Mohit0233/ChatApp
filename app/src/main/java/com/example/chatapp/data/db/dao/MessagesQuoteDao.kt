package com.example.chatapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.chatapp.data.db.entity.msgstore.MessagesQuote
@Dao
interface MessagesQuoteDao {


    @Insert
    suspend fun addMessagesQuote(messagesQuote: MessagesQuote)

    @Query( "SELECT * FROM messages_quotes ORDER BY _id")
    fun readAllMessagesQuote(): LiveData<List<MessagesQuote>>
}