package com.example.chatapp.ui

import androidx.lifecycle.ViewModel
import com.example.chatapp.data.LoginRepository

class MainViewModel(
  private val loginRepository: LoginRepository
) : ViewModel() {
  var user = loginRepository.getUser()
}