package com.example.chatapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.chatapp.data.db.entity.chatapp.WaContact

@Dao
interface WaContactDao {

    @Insert
    suspend fun addContact(waContact: WaContact)

    @Query( "SELECT * FROM wa_contacts ORDER BY _id")
    fun readAllContacts(): LiveData<List<WaContact>>
}