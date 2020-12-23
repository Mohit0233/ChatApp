package com.example.chatapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.R
import com.example.chatapp.data.firebase.FirebaseSource
import com.example.chatapp.utils.startHomeActivity
import com.example.chatapp.utils.startLoginActivity
import com.example.chatapp.ui.base.BaseViewModelFactory
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, BaseViewModelFactory(FirebaseSource()))
            .get(MainViewModel::class.java)

        if (viewModel.user == null) {
            this.startLoginActivity()
        } else {
            this.startHomeActivity()
        }
        overridePendingTransition(0, 0)
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }
}