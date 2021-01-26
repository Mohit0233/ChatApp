package com.example.chatapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.chatapp.data.db.entity.msgstore.ChatList

@Dao
interface ChatListDao {

    @Insert
    suspend fun addChatList(chatList: ChatList)

    @Query( "SELECT * FROM chat_list ORDER BY _id")
    fun readAllChatLists(): LiveData<List<ChatList>>//TODO("check if it should be suspended or not")
}