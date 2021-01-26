package com.example.chatapp.ui.login

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SubscriptionInfo
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.SparseArray
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.chatapp.R
import com.example.chatapp.ui.login.CountryPickerActivity.Companion.COUNTRY_CALLING_CODE_STRING_EXTRA
import com.example.chatapp.ui.login.CountryPickerActivity.Companion.COUNTRY_STRING_EXTRA
import com.example.chatapp.ui.login.VerifySmsActivity.Companion.CCC_STRING_EXTRA
import com.example.chatapp.ui.login.VerifySmsActivity.Companion.PHONE_STRING_EXTRA
import com.example.chatapp.ui.model.CountryTsvData
import com.example.chatapp.ui.model.SimData
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.util.*


class RegisterPhoneActivity : AppCompatActivity(),
    SelectPhoneNumberDialogFragment.NoticeDialogListener {

    private var countryCallingCode: String? = null
    private var country: String? = null
    private lateinit var registrationCountry: TextView
    private lateinit var registrationCc: EditText
    private lateinit var registrationPhone: EditText
    private lateinit var simDataArrayList: ArrayList<SimData>
    private lateinit var arrayList: ArrayList<CountryTsvData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_phone)
        val whatsMyNumber = findViewById<TextView>(R.id.whats_my_number)
        registrationCountry = findViewById(R.id.registration_country)
        registrationCc = findViewById(R.id.registration_cc)
        registrationPhone = findViewById(R.id.registration_phone)
        val registrationSubmit: Button = findViewById(R.id.registration_submit)


        val telephonyManager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager

        whatsMyNumber.setOnClickListener {

            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_PHONE_STATE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                simDataArrayList = ArrayList()
                val subscriptionManager: SubscriptionManager? =
                    getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE) as SubscriptionManager?
                if (subscriptionManager != null) {
                    val activeSubscriptionInfoList: List<SubscriptionInfo> =
                        subscriptionManager.activeSubscriptionInfoList
                    for (next in activeSubscriptionInfoList) {
                        simDataArrayList.add(
                            SimData(
                                next.number,
                                next.carrierName.toString(),
                                next.countryIso,
                                false
                            )
                        )
                        simDataArrayList[0].isSelected = true
                    }
                }
                //Dialog()
                val dialog = SelectPhoneNumberDialogFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList(
                    ARRAY_LIST_SIM_DATA_PARCELABLE_EXTRA,
                    simDataArrayList
                )
                dialog.arguments = bundle
                dialog.show(supportFragmentManager, "SelectPhoneNumberDialog")
            } else {
                //Todo Open Dialog permission READ_PHONE_STATE
            }

        }

        // returns lowercase 2 char or empty string
        arrayList = loadCountryResources()
        val simCountryIso: String = telephonyManager.simCountryIso
        countryCallingCode = getCountryCallingCode(simCountryIso, arrayList)
        country = getCountryName(simCountryIso, arrayList)

        registrationCountry.text = country
        registrationCc.setText(countryCallingCode.toString())

        registrationCc.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val ccc = s.toString()
                if (ccc.isNotEmpty()) {
                    val countryName = getCountryNameByCcc(ccc.toInt(), arrayList)
                    if (countryName != null) {
                        registrationCountry.text = countryName
                        registrationPhone.requestFocus()
                    } else {
                        registrationCountry.text = getString(R.string.register_bad_cc_valid)
                    }
                } else {
                    registrationCountry.text = getString(R.string.register_bad_cc_valid)
                }
            }

        })

        registrationCountry.setOnClickListener {
            startActivityForResult(
                Intent(this, CountryPickerActivity::class.java).apply {
                    putExtra(COUNTRY_CALLING_CODE_STRING_EXTRA, countryCallingCode!!)
                    putExtra(COUNTRY_STRING_EXTRA, country)
                }, SECOND_ACTIVITY_REQUEST_CODE
            )
        }

        registrationSubmit.setOnClickListener {
            val ccc: String = registrationCc.text.toString()
            val country = registrationCountry.text
            val phone: String = registrationPhone.text.toString().replace(" ", "")
            if (ccc.isEmpty() || ccc.length > 3) {
                Toast.makeText(
                    this,
                    getString(R.string.register_bad_cc_length_with_placeholders),
                    Toast.LENGTH_SHORT
                ).show()
            } else if (getCountryNameByCcc(ccc.toInt(), arrayList) == null) {
                Toast.makeText(this, getString(R.string.register_bad_cc_valid), Toast.LENGTH_SHORT)
                    .show()
            } else if (phone == "") {
                Toast.makeText(
                    this,
                    "Invalid Phone Number should be of 10 digits according to $country",
                    Toast.LENGTH_SHORT
                ).show()
            }else {
                Toast.makeText(this, "$ccc $phone $country", Toast.LENGTH_SHORT).show()
                Intent(this, VerifySmsActivity::class.java).apply {
                    putExtra(CCC_STRING_EXTRA, ccc)
                    putExtra(PHONE_STRING_EXTRA, phone)
                    flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NO_ANIMATION
                    startActivity(this)
                }
            }
        }
        registrationPhone.requestFocus()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                countryCallingCode = data!!.getStringExtra(COUNTRY_CALLING_CODE_STRING_EXTRA)
                country = data.getStringExtra(COUNTRY_STRING_EXTRA)
                registrationCc.setText(countryCallingCode)
                registrationCountry.text = country
            }
        }
    }

    override fun onDialogPositiveClick(dialog: DialogInterface, position: Int) {
        registrationCountry.text = getCountryName(simDataArrayList[position].countryIso, arrayList)
        registrationPhone.setText(simDataArrayList[position].number)
        registrationCc.setText(
            getCountryCallingCode(
                simDataArrayList[position].countryIso,
                arrayList
            )
        )
        dialog.dismiss()
    }

    private fun getCountryNameByCcc(ccc: Int, arrayList: ArrayList<CountryTsvData>): String? {
        for (c12700iE in arrayList) {
            if (c12700iE.countryCallingCodeInt == ccc) {
                return c12700iE.realIndex12
            }
        }
        return null
    }

    private fun getCountryCallingCode(
        simCountryIso: String,
        arrayList: ArrayList<CountryTsvData>
    ): String? {
        val sparseArray = SparseArray<CountryTsvData>(arrayList.size)
        for (o in arrayList) sparseArray.append(ccToInt(o.cc), o)
        val length: Int = simCountryIso.length

        return when {
            length == 2 -> {
                val c12700iE = sparseArray.get(ccToInt(simCountryIso.toUpperCase(Locale.US)))
                c12700iE.countryCallingCodeInt.toString()
            }
            length != 3 || simCountryIso == "999" -> {
                null
            }
            else -> {
                // todo maybe never used as len should always be 2 of e
                for (o in arrayList) {

                    for (append in o.contains404and405) {
                        val sb = "" + append
                        if (simCountryIso == sb) {
                            o.countryCallingCodeInt.toString()
                        }
                    }
                }
                null
            }
        }
    }

    private fun getCountryName(
        simCountryISO: String,
        arrayList: ArrayList<CountryTsvData>
    ): String {
        if (simCountryISO.length < 2) return getString(R.string.register_bad_cc_valid)
        val sparseArray = SparseArray<CountryTsvData>(arrayList.size)
        for (o in arrayList) sparseArray.append(ccToInt(o.cc), o)
        val c12700iE = sparseArray.get(ccToInt(simCountryISO.toUpperCase(Locale.US)))
        return c12700iE.realIndex12
    }

    private fun loadCountryResources(): ArrayList<CountryTsvData> {

        val arrayList = ArrayList<CountryTsvData>(243)
        val bufferedReader = BufferedReader(
            InputStreamReader(
                resources.openRawResource(R.raw.countries),
                StandardCharsets.UTF_8
            )
        )
        var r4 = bufferedReader.readLine()//error
        while (r4 != null) {
            val r2 = TextUtils.split(r4, "\t") ?: throw IllegalArgumentException()
            require(r2.size >= 12)
            val c12700iE = CountryTsvData(r2)
            arrayList.add(c12700iE)
            r4 = bufferedReader.readLine()
        }
        bufferedReader.close()
        return arrayList
    }

    private fun ccToInt(str: String) =
        if (str.length != 2 || 'A' > str[0]
            || str[0] > 'Z' || 'A' > str[1]
            || str[1] > 'Z'
        ) {
            -1
        } else str[1] - 'A' + (str[0] - 'A') * 26

    companion object {
        private const val SECOND_ACTIVITY_REQUEST_CODE = 0
        const val ARRAY_LIST_SIM_DATA_PARCELABLE_EXTRA = "ARRAY_LIST_SIM_DATA_PARCELABLE_EXTRA"
    }
}
