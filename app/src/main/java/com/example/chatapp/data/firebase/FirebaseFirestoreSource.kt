package com.example.chatapp.data.firebase

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseFirestoreSource {

    private val db = Firebase.firestore

    fun getDatabase() = db

    fun getDocRef(docId: String)
        = db.collection("users")
                .document(docId)



    companion object {
        private val TAG = "Firebase Firestore"

    }

}