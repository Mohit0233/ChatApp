package com.example.chatapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.chatapp.data.local.model.ChatList

@Dao
interface ChatListDao {

    @Insert
    suspend fun addChatList(chatList: ChatList)

    @Query( "SELECT * FROM chat_list ORDER BY id")
    fun readAllChatLists(): LiveData<List<ChatList>>
}