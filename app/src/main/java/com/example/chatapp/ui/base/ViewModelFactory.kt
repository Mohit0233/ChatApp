package com.example.chatapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chatapp.data.LoginDataSource
import com.example.chatapp.data.repository.LoginRepository
import com.example.chatapp.data.firebase.FirebaseAuthSource
import com.example.chatapp.data.db.ChatApp
import com.example.chatapp.data.db.MsgStore
import com.example.chatapp.data.repository.ChatRoomRepository
import com.example.chatapp.ui.MainViewModel
import com.example.chatapp.ui.chatroom.ChatRoomViewModel
import com.example.chatapp.ui.home.viewmodel.ChatsViewModel
import com.example.chatapp.ui.login.AuthViewModel

class ViewModelFactory(
    private val firebaseAuthSource: FirebaseAuthSource,
    private val msgStore: MsgStore?,
    private val chatApp: ChatApp?,
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(
                LoginRepository(
                    LoginDataSource(firebaseAuthSource)
                )
            ) as T
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(
                LoginRepository(
                    LoginDataSource(firebaseAuthSource)
                )
            ) as T
            modelClass.isAssignableFrom(ChatRoomViewModel::class.java) -> ChatRoomViewModel(
                ChatRoomRepository(
                    msgStore!!,
                    firebaseAuthSource
                )
            ) as T
            modelClass.isAssignableFrom(ChatsViewModel::class.java) -> ChatsViewModel(
                msgStore!!
            ) as T
            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }
}