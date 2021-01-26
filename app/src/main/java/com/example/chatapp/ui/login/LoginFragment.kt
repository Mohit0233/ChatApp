package com.example.chatapp.ui.login

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.chatapp.R
import com.example.chatapp.data.db.ChatApp
import com.example.chatapp.data.db.MsgStore
import com.example.chatapp.data.firebase.FirebaseAuthSource
import com.example.chatapp.ui.base.ViewModelFactory
import com.example.chatapp.utils.afterTextChanged
import com.example.chatapp.utils.showLoginFailed
import com.example.chatapp.utils.updateUiWithUser

class LoginFragment : Fragment() {

    private lateinit var authViewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_login, container, false)
        val username = root.findViewById<EditText>(R.id.username)
        val password = root.findViewById<EditText>(R.id.password)
        val login = root.findViewById<Button>(R.id.register)
        val registerInstead = root.findViewById<TextView>(R.id.registerInstead)
        val loading = root.findViewById<ProgressBar>(R.id.loading)

        authViewModel = ViewModelProvider(this, ViewModelFactory(FirebaseAuthSource(null), null, null))
            .get(AuthViewModel::class.java)

        authViewModel.authFormState.observe(viewLifecycleOwner, Observer {
            val authState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = authState.isDataValid

            if (authState.usernameError != null) {
                username.error = getString(authState.usernameError)
            }
            if (authState.passwordError != null) {
                password.error = getString(authState.passwordError)
            }
        })

        authViewModel.authResult.observe(viewLifecycleOwner, Observer {
            val authResult = it ?: return@Observer

            Log.e("Login Must be successful","${authResult.success} && ${authResult.error}")
            loading.visibility = View.GONE
            if (authResult.error != null) {
                requireContext().showLoginFailed(authResult.error)
            }
            if (authResult.success != null) {
                requireContext().updateUiWithUser(authResult.success)
                requireActivity().setResult(Activity.RESULT_OK)
                //Complete and destroy login activity once successful
                requireActivity().finish()
                Log.e("Login Must be successful","yeah")
            }

            //authViewModel.logout()

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
                        authViewModel.login(
                            username.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                authViewModel.login(username.text.toString(), password.text.toString())
            }
        }
        registerInstead.setOnClickListener {
            Navigation.findNavController(requireActivity(),R.id.fragment).navigate(
                LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }
        return root
    }



}