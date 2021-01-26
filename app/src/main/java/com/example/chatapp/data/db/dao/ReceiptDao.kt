package com.example.chatapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.chatapp.data.db.entity.msgstore.Receipts

@Dao
interface ReceiptDao {
    @Insert
    suspend fun addReceipt(receipts: Receipts)

    @Query( "SELECT * FROM receipts ORDER BY _id")
    fun readAllReceipt(): LiveData<List<Receipts>>
}