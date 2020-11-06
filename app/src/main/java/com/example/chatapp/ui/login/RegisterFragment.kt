package com.example.chatapp.ui.login

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.chatapp.R
import com.example.chatapp.data.firebase.FirebaseSource
import com.example.chatapp.ui.base.BaseViewModelFactory
import com.example.chatapp.utils.afterTextChanged
import com.example.chatapp.utils.showLoginFailed
import com.example.chatapp.utils.updateUiWithUser
import kotlinx.android.synthetic.main.fragment_login.view.loading
import kotlinx.android.synthetic.main.fragment_login.view.password
import kotlinx.android.synthetic.main.fragment_login.view.register
import kotlinx.android.synthetic.main.fragment_login.view.username
import kotlinx.android.synthetic.main.fragment_register.view.*

class RegisterFragment : Fragment() {

    private lateinit var authViewModel: AuthViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_register, container, false)
        val username = root.username
        val password = root.password
        val register = root.register
        val loginInstead = root.loginInstead
        val loading = root.loading

        authViewModel = ViewModelProvider(this, BaseViewModelFactory(FirebaseSource()))
            .get(AuthViewModel::class.java)

        authViewModel.authFormState.observe(viewLifecycleOwner, Observer {
            val authState = it ?: return@Observer

            // disable login button unless both username / password is valid
            register.isEnabled = authState.isDataValid

            if (authState.usernameError != null) {
                username.error = getString(authState.usernameError)
            }
            if (authState.passwordError != null) {
                password.error = getString(authState.passwordError)
            }
        })

        authViewModel.authResult.observe(viewLifecycleOwner, Observer {
            val authResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (authResult.error != null) {
                requireContext().showLoginFailed(authResult.error)
            }
            if (authResult.success != null) {
                requireContext().updateUiWithUser(authResult.success)
            }
            requireActivity().setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
            requireActivity().finish()
        })

        username.afterTextChanged {
            authViewModel.authDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                authViewModel.authDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        authViewModel.register(
                            username.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            register.setOnClickListener {
                loading.visibility = View.VISIBLE
                authViewModel.register(username.text.toString(), password.text.toString())
            }
        }
        loginInstead.setOnClickListener {
            Navigation.findNavController(requireActivity(),R.id.fragment).navigate(
            RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }
        return root
    }
}