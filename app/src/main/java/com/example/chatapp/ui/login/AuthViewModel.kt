package com.example.chatapp.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatapp.R
import com.example.chatapp.data.LoginRepository
import com.example.chatapp.data.firebase.FirebaseSource
import com.example.chatapp.utils.isPasswordValid
import com.example.chatapp.utils.isUserNameValid
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AuthViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<AuthFormState>()
    val authFormState: LiveData<AuthFormState> = _loginForm

    private val _authResult = MutableLiveData<AuthResult>()
    val authResult: LiveData<AuthResult> = _authResult

    private val disposables = CompositeDisposable()

    fun login(username: String, password: String) {
        val disposable = loginRepository.login(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.e("${loginRepository.getUser()?.email} +++ ${FirebaseSource().currentUser()?.email}", "Auth Result success")
                _authResult.value = AuthResult(success = loginRepository.getUser())
            }, {
                //sending a failure callback
                _authResult.value = AuthResult(error = it.message!!)
            })
        disposables.add(disposable)
    }

    fun register(username: String, password: String) {
        val disposable = loginRepository.register(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.e("${loginRepository.getUser()?.email}", "Auth Result success")
                _authResult.value = AuthResult(success = loginRepository.getUser())
            }, {
                //sending a failure callback
                _authResult.value = AuthResult(error = it.message!!)
            })
        disposables.add(disposable)
    }

    fun authDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = AuthFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = AuthFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = AuthFormState(isDataValid = true)
        }
    }

    fun logout() {
        loginRepository.logout()
    }

    //disposing the disposables
    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

}