package com.example.chatapp.data

import com.example.chatapp.data.firebase.FirebaseAuthSource

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource(private val firebaseAuthSource: FirebaseAuthSource) {

    //private val firebaseAuthSource = FirebaseAuthSource()
    fun login(username: String, password: String) = firebaseAuthSource.login(username, password)

    fun register(username: String, password: String) = firebaseAuthSource.register(username, password)

    fun logout() = firebaseAuthSource.signOut()

    fun currentUser() = firebaseAuthSource.currentUser()
}