package com.example.chatapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.chatapp.data.db.entity.msgstore.Messages

@Dao
interface MessageDao {

    @Insert
    suspend fun addMessage(messages: Messages)

    @Query( "SELECT * FROM messages ORDER BY _id")
    fun readAllMessages(): LiveData<List<Messages>>
}