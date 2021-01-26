package com.example.chatapp.data.firebase

import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class FirebaseStorageSource {
    val storage = Firebase.storage

    fun delete(string: String) {
        storage.reference.child(string).delete().addOnSuccessListener{
            Log.i(TAG, "12: @@@@@@ File Deleted From Storage")
        }.addOnFailureListener {
            Log.e(TAG, "14: File Deleted From Storage")
        }
    }

    companion object {
        const val TAG = "Firestore Storage"
    }

}