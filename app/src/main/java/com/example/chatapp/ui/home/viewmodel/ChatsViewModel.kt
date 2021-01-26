package com.example.chatapp.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import com.example.chatapp.data.db.MsgStore

class ChatsViewModel(
    private val msgStore: MsgStore
) : ViewModel() {

    var st: String = ""


}