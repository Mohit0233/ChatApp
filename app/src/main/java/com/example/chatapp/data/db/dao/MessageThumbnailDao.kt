package com.example.chatapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.chatapp.data.db.entity.msgstore.MessageThumbnails

@Dao
interface MessageThumbnailDao {
    @Insert
    suspend fun addMessageThumbnail(messageThumbnails: MessageThumbnails)

    @Query( "SELECT * FROM message_thumbnails ORDER BY key_remote_jid")
    fun readAllMessageThumbnail(): LiveData<List<MessageThumbnails>>
}