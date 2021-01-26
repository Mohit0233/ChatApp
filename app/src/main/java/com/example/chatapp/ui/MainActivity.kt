package com.example.chatapp.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.R
import com.example.chatapp.data.firebase.FirebaseAuthSource
import com.example.chatapp.ui.base.ViewModelFactory
import com.example.chatapp.utils.startHomeActivity
import com.example.chatapp.utils.startRegisterNameActivity
import com.example.chatapp.utils.startRegisterPhoneActivity

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    fun loadData(): Boolean {
        getSharedPreferences(SHARED_PREFS, MODE_PRIVATE).getString(FLAG_HAVE_NAME, "").apply {
            Log.e("♥♥♥♥♥♥♥♥♥♥♥♥",this.toString())
            return this != null || this != "" || this != " "
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, ViewModelFactory(FirebaseAuthSource(null), null, null))
            .get(MainViewModel::class.java)

        //Log.e("displayName",viewModel.user!!.displayName.toString())
        when {
            viewModel.user == null -> {
                startRegisterPhoneActivity()
            }
            viewModel.user!!.displayName == null -> {
                startRegisterNameActivity()
            }
            else -> {
                startHomeActivity()
            }
        }
        overridePendingTransition(0, 0)
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

    companion object {
        const val SHARED_PREFS = "sharedPrefs"
        const val FLAG_HAVE_NAME = "text"
    }
}