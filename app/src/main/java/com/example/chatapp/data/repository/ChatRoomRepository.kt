package com.example.chatapp.data.repository

import com.example.chatapp.data.firebase.FirebaseAuthSource
import com.example.chatapp.data.db.MsgStore
import com.example.chatapp.data.db.entity.msgstore.Messages

class ChatRoomRepository(
    private val msgStore: MsgStore,
    private val firebaseAuthSource: FirebaseAuthSource
) {

    fun getAllMsg() = msgStore.messageDao().readAllMessages()

    suspend fun insertMessage(messages: Messages) = run {
        msgStore.messageDao().addMessage(messages)
        firebaseAuthSource
    }
}