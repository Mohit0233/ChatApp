package com.example.chatapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.chatapp.data.db.entity.msgstore.MediaStreamingSidecar
@Dao
interface MediaStreamingSidecarDao {


    @Insert
    suspend fun addMediaStreamingSidecar(mediaStreamingSidecar: MediaStreamingSidecar)

    @Query( "SELECT * FROM media_streaming_sidecar ORDER BY _id")
    fun readAllMediaStreamingSidecar(): LiveData<List<MediaStreamingSidecar>>
}