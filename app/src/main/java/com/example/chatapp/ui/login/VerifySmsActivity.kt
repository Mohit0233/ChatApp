package com.example.chatapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.chatapp.R
import com.example.chatapp.data.firebase.FirebaseAuthSource
import com.example.chatapp.data.firebase.FirebaseFirestoreSource
import com.example.chatapp.data.model.OtpVerification
import com.example.chatapp.utils.startRegisterNameActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.firebase.auth.PhoneAuthProvider

class VerifySmsActivity : AppCompatActivity(),
    OtpVerification {

    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var ccc: String
    private lateinit var phone: String
    private lateinit var verificationId: String
    private lateinit var firebaseFirestoreSource: FirebaseFirestoreSource
    private lateinit var firebaseAuthSource: FirebaseAuthSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_sms)

        val bundle = intent.extras!!
        ccc = bundle.getString(CCC_STRING_EXTRA)!!
        phone = bundle.getString(PHONE_STRING_EXTRA)!!

        val titleToolbar = findViewById<MaterialToolbar>(R.id.title_toolbar)
        val titleToolbarText = findViewById<TextView>(R.id.title_toolbar_text)
        val scrollView = findViewById<ScrollView>(R.id.scroll_view)
        val description2Top = findViewById<TextView>(R.id.description_2_top)
        val wrongNumberTextView = findViewById<TextView>(R.id.wrongNumberTextView)
        val roamingWarning = findViewById<TextView>(R.id.roaming_warning)
        val smsPaneCallLayout = findViewById<RelativeLayout>(R.id.sms_pane_call_layout)
        val codeInput = findViewById<RelativeLayout>(R.id.code_input)
        val verifySmsCodeInput = findViewById<EditText>(R.id.verify_sms_code_input)
        val progressBarCodeInputBlocked =
            findViewById<ProgressBar>(R.id.progress_bar_code_input_blocked)
        val verifySmsRetryBtn = findViewById<ImageButton>(R.id.verify_sms_retry_btn)
        val description2Bottom = findViewById<TextView>(R.id.description_2_bottom)
        val smsProgressGroup = findViewById<LinearLayout>(R.id.sms_progress_group)
        val resendSmsBtn = findViewById<Button>(R.id.resend_sms_btn)
        val countdownTimeSms = findViewById<TextView>(R.id.countdown_time_sms)
        val countdownTimeVoice = findViewById<TextView>(R.id.countdown_time_voice)
        val voiceProgressGroup = findViewById<LinearLayout>(R.id.voice_progress_group)
        val callBtn = findViewById<Button>(R.id.call_btn)

        /*
        *
        *
        *
        *
        *
        *
        * //todo add chronometer for resend
        *
        *
        *
        *
        *
        *
        * */

        val description2String =
            "Waiting to automatically detect an SMS sent to $ccc $phone."//todo make phone number bold
        description2Top.text = description2String
        val wrNoString = "Wrong number?"
        wrongNumberTextView.text = wrNoString

        verifySmsCodeInput.isEnabled = false
        verifySmsRetryBtn.isEnabled = false
        resendSmsBtn.isEnabled = false
        callBtn.isEnabled = false

        wrongNumberTextView.setOnClickListener {
            Intent(this, RegisterPhoneActivity::class.java).also {
                it.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NO_ANIMATION
                startActivity(it)
            }
        }


        //todo firebase init remove it and use viewmodel
        firebaseAuthSource = FirebaseAuthSource(this)
        firebaseAuthSource.startPhoneNumberVerification(this, "+$ccc$phone")
        firebaseFirestoreSource = FirebaseFirestoreSource()


        firebaseAuthSource.storedVerificationId.observe(this, { verificationId ->
            if (verificationId != null && verificationId != "") {
                this.verificationId = verificationId
                this.resendToken = firebaseAuthSource.resendToken
                verifySmsCodeInput.isEnabled = true
            }
        })

        var code: String
        verifySmsRetryBtn.setOnClickListener {
            code = verifySmsCodeInput.text.toString()
            firebaseAuthSource.verifyPhoneNumberWithCode(verificationId, code)
        }

        resendSmsBtn.setOnClickListener {
            firebaseAuthSource.resendVerificationCode(this, phone, token = resendToken)
        }

    }

    override fun onSignInWithCredentialSuccessful() {
        /*val login = hashMapOf(
            "time" to System.currentTimeMillis(),
            "" to "CA",
            "country" to "USA"
        )*/
        /*firebaseFirestoreSource.getDatabase().collection("users")
            .document(firebaseAuthSource.currentUser()?.uid.toString())
            */
        startRegisterNameActivity()
    }

    override fun onSignInWithCredentialFailed() {
        Toast.makeText(this, "Error VerifySmsActivity line 86", Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val CCC_STRING_EXTRA = "CCC_STRING_EXTRA"
        const val PHONE_STRING_EXTRA = "PHONE_NUMBER_STRING_EXTRA"
    }
}