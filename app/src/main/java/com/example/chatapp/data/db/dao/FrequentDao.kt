package com.example.chatapp.data.db.dao

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.chatapp.data.db.entity.msgstore.Frequents
import kotlinx.coroutines.flow.Flow

@Dao
interface FrequentDao {
    @Insert
    suspend fun addFrequent(frequent: Frequents)

    @Query("SELECT * FROM frequents ORDER BY _id")
    fun readAllFrequent(): Flow<List<Frequents>>

}