package com.example.chatapp.data.firebase

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.chatapp.data.model.OtpVerification
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import io.reactivex.Completable
import java.util.concurrent.TimeUnit

class FirebaseAuthSource(
    private val listener: OtpVerification? //try to make it not null
) {
    //Todo this is java based convert into kotlin (see in official docs)
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private var _storedVerificationId: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    var storedVerificationId: LiveData<String> = _storedVerificationId


    private var user = auth.currentUser

    fun login(email: String, password: String) = Completable.create { emitter ->
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (!emitter.isDisposed) {
                if (it.isSuccessful) {
                    Log.e("user", "${it.result?.user?.email}")
                    user = it.result?.user
                    emitter.onComplete()
                } else
                    emitter.onError(it.exception!!)
            }
        }
    }

    fun register(email: String, password: String) = Completable.create { emitter ->
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (!emitter.isDisposed) {
                if (it.isSuccessful) {
                    user = it.result?.user
                    emitter.onComplete()
                } else
                    emitter.onError(it.exception!!)
            }
        }
    }


    fun startPhoneNumberVerification(
        activity: Activity,
        phoneNumber: String
    ) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
//Todo 60 sec
        //verificationInProgress = true
    }

    private var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.

                var code = credential.smsCode

                Log.d(TAG, "onVerificationCompleted:$credential")
                //verificationInProgress = false

                // Update the UI and attempt sign in with the phone credential
                //updateUI(STATE_VERIFY_SUCCESS, credential)
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                // for instance if the the phone number format is not valid.
                Log.w(TAG, "onVerificationFailed", e)
                //verificationInProgress = false
                if (e is FirebaseAuthInvalidCredentialsException) {
                    // "Invalid phone number." and invalid request
                } else if (e is FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                }
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:$verificationId")

                // Save verification ID and resending token so we can use them later
                resendToken = token
                _storedVerificationId.value = verificationId

                // STATE_CODE_SENT
            }
        }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential).addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")

                    val user = task.result?.user
                    listener!!.onSignInWithCredentialSuccessful()

                    // STATE_SIGNIN_SUCCESS

                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        //"Invalid code."
                        listener!!.onSignInWithCredentialFailed()
                    }
                }
            }
    }


    fun resendVerificationCode(
        activity: Activity,
        phoneNumber: String,
        token: PhoneAuthProvider.ForceResendingToken?
    ) {
        val optionsBuilder = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
        if (token != null) {
            optionsBuilder.setForceResendingToken(token) // callback's ForceResendingToken
        }
        PhoneAuthProvider.verifyPhoneNumber(optionsBuilder.build())
    }

    fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {

        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)

        signInWithPhoneAuthCredential(credential)
    }

    fun signOut() = auth.signOut()
    fun currentUser() = user



    companion object {
        private const val TAG = "Firebase Auth"
    }
    //todo start verification   startPhoneNumberVerification(binding.fieldPhoneNumber.text.toString())

    // todo verify phone number with code verifyPhoneNumberWithCode(storedVerificationId, code)

    //todo resend verification code resendVerificationCode(phoneNumber = "", resendToken)

}
