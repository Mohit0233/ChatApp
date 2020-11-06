package com.example.chatapp

import android.app.Application
import com.google.firebase.FirebaseApp
//not used until now
class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}