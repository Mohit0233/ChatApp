package com.example.chatapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.chatapp.data.db.entity.msgstore.GroupParticipantsHistory
@Dao
interface GroupParticipantsHistoryDao {

    @Insert
    suspend fun add(groupParticipantHistory: GroupParticipantsHistory)

    @Query( "SELECT * FROM group_participants_history ORDER BY _id")
    fun readAll(): LiveData<List<GroupParticipantsHistory>>
}