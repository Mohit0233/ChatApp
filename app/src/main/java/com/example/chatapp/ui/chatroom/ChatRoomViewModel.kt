package com.example.chatapp.ui.chatroom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp.data.db.entity.msgstore.Messages
import com.example.chatapp.data.repository.ChatRoomRepository
import kotlinx.coroutines.launch

class ChatRoomViewModel(
    private val chatRoomRepository: ChatRoomRepository
) : ViewModel() {

    fun getAllMsg() = chatRoomRepository.getAllMsg()

    fun writeMsg(messages: Messages) = run {
        viewModelScope.launch {
            chatRoomRepository.insertMessage(messages)
        }
    }
}