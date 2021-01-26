package com.example.chatapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.chatapp.data.db.entity.msgstore.StatusList

@Dao
interface StatusListDao {
    @Insert
    suspend fun addStatusList(statusList: StatusList)

    @Query( "SELECT * FROM status_list ORDER BY _id")
    fun readAllStatusList(): LiveData<List<StatusList>>
}