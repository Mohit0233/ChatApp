package com.example.chatapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.chatapp.data.db.entity.msgstore.MediaRefs
@Dao
interface MediaRefDao {

    @Insert
    suspend fun addMediaRef(mediaRefs: MediaRefs)

    @Query( "SELECT * FROM  media_refs ORDER BY _id")
    fun readAllMediaRef(): LiveData<List<MediaRefs>>
}