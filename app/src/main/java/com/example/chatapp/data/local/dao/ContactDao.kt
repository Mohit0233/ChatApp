package com.example.chatapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.chatapp.data.local.model.Contact

@Dao
interface ContactDao {

    @Insert
    suspend fun addContact(contact: Contact)

    @Query( "SELECT * FROM wa_contacts ORDER BY _id")
    fun readAllContacts(): LiveData<List<Contact>>
}