package com.example.chatapp.data

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(
    private val dataSource: LoginDataSource
) {


    fun getUser() =
        dataSource.currentUser()
    val isLoggedIn: Boolean
        get() = dataSource.currentUser() != null

    fun logout() {
        dataSource.logout()
    }

    fun login(username: String, password: String) = dataSource.login(username, password)

    fun register(username: String, password: String) = dataSource.register(username, password)

}