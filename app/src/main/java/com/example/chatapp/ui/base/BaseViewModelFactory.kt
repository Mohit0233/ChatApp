package com.example.chatapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.data.LoginDataSource
import com.example.chatapp.data.LoginRepository
import com.example.chatapp.data.firebase.FirebaseSource
import com.example.chatapp.ui.MainViewModel
import com.example.chatapp.ui.login.AuthViewModel

class BaseViewModelFactory(private val firebaseSource: FirebaseSource) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(
                LoginRepository(
                    LoginDataSource(firebaseSource)
                )
            ) as T
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(
                LoginRepository(
                    LoginDataSource(firebaseSource)
                )
            ) as T
            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }
}