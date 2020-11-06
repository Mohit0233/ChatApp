package com.example.chatapp.utils

import android.util.Patterns


// A placeholder username validation check
fun isUserNameValid(username: String): Boolean {
    return if (username.contains('@')) {
        Patterns.EMAIL_ADDRESS.matcher(username).matches()
    } else {
        username.isNotBlank()
    }
}

// A placeholder password validation check
fun isPasswordValid(password: String): Boolean {
    return password.length > 5
}

