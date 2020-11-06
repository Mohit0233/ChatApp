package com.example.chatapp.data

import com.example.chatapp.data.firebase.FirebaseSource
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource(private val firebaseSource: FirebaseSource) {

    //private val firebaseSource = FirebaseSource()
    fun login(username: String, password: String) = firebaseSource.login(username, password)

    fun register(username: String, password: String) = firebaseSource.register(username, password)

    fun logout() = firebaseSource.logout()

    fun currentUser() = firebaseSource.currentUser()
}