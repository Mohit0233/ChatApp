package com.example.chatapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.chatapp.data.db.dao.WaContactDao
import com.example.chatapp.data.db.entity.chatapp.*

@Database(
    entities = [
        WaContact::class,
        WaBizProfiles::class,
        WaBizProfilesHours::class,
        WaBizProfilesWebsites::class,
        WaBlockList::class,
        WaContactStorageUsage::class,
        WaGroupAddBlackList::class,
        WaGroupAdminSettings::class,
        WaGroupDescriptions::class,
        WaVnames::class,
        WaVnamesLocalized::class,
    ], version = 1,
    exportSchema = false
)
abstract class ChatApp : RoomDatabase() {

    abstract fun contactDao(): WaContactDao

    companion object {
        @Volatile
        private var INSTANCE: ChatApp? = null

        fun getDatabase(context: Context): ChatApp {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ChatApp::class.java,
                    "ca"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}