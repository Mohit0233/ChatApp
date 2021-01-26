package com.example.chatapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.chatapp.data.db.entity.msgstore.GroupParticipants
@Dao
interface GroupParticipantDao {


    @Insert
    suspend fun addGroupParticipant(groupParticipants: GroupParticipants)

    @Query( "SELECT * FROM group_participants ORDER BY _id")
    fun readAllGroupParticipant(): LiveData<List<GroupParticipants>>
}