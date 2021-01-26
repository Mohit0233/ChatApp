package com.example.chatapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.chatapp.data.db.entity.msgstore.MessagesEdit
@Dao
interface MessagesEditDao {


    @Insert
    suspend fun addMessagesEdit(messagesEdit: MessagesEdit)

    @Query( "SELECT * FROM messages_edits ORDER BY _id")
    fun readAllMessagesEdit(): LiveData<List<MessagesEdit>>
}