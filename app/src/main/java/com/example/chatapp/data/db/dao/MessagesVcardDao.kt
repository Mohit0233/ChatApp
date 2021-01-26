package com.example.chatapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.chatapp.data.db.entity.msgstore.MessagesVcard
@Dao
interface MessagesVcardDao {


    @Insert
    suspend fun addMessagesVcard(messagesVcard: MessagesVcard)

    @Query( "SELECT * FROM messages_vcards ORDER BY _id")
    fun readAllMessagesVcard(): LiveData<List<MessagesVcard>>
}