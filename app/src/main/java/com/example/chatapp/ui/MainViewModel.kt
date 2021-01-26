package com.example.chatapp.ui

import androidx.lifecycle.ViewModel
import com.example.chatapp.data.repository.LoginRepository

class MainViewModel(
  loginRepository: LoginRepository
) : ViewModel() {
  var user = loginRepository.getUser()
}