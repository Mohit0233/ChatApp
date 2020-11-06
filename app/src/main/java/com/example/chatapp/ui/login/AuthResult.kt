package com.example.chatapp.ui.login

import com.google.firebase.auth.FirebaseUser

/**
 * Authentication result : success (user details) or error message.
 */
data class AuthResult(
    val success: FirebaseUser? = null,
    val error: String? = null
)