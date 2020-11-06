package com.example.chatapp.utils

import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import com.example.chatapp.R
import com.example.chatapp.ui.HomeActivity
import com.example.chatapp.ui.login.AuthActivity
import com.google.firebase.auth.FirebaseUser

fun Context.startHomeActivity() =
    Intent(this, HomeActivity::class.java).also {
        it.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NO_ANIMATION
        startActivity(it)
    }

fun Context.startLoginActivity() =
    Intent(this, AuthActivity::class.java).also {
        it.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NO_ANIMATION
        startActivity(it)
    }

fun Context.updateUiWithUser(model: FirebaseUser) {
    val welcome = getString(R.string.welcome)
    val displayName = model.displayName
    this.startHomeActivity()
    Toast.makeText(
        this,
        "$welcome $displayName",
        Toast.LENGTH_LONG
    ).show()
}

fun Context.showLoginFailed(errorString: String) {
    Toast.makeText(this, errorString, Toast.LENGTH_SHORT).show()
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}