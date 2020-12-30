package com.example.chatapp.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [], version = 1)
abstract class MsgStore : RoomDatabase() {

    //abstract fun messagesDao(): MessageDao

    companion object {
        @Volatile
        private var INSTANCE: MsgStore? = null;

        fun getDatabase(context: Context): MsgStore {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MsgStore::class.java,
                    "msgstore"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}