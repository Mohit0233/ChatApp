package com.example.chatapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.chatapp.data.local.model.Message

@Dao
interface MessageDao {

    @Insert
    suspend fun addMessage(message: Message)

    @Query( "SELECT * FROM messages ORDER BY _id")
    fun readAllMessages(): LiveData<List<Message>>
}