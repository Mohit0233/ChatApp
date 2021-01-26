package com.example.chatapp.ui.model

import android.text.TextUtils

class CountryTsvData(stringsCountryTsv: Array<String>) {
    var countryCallingCodeInt = 0
    var realIndex11: String
    var realIndex12: String
    var cc: String
    var country: String
    var realIndex14: String
    var aBoolean = false
    lateinit var realIndex5: IntArray
    lateinit var contains404and405: IntArray
    lateinit var realIndex9: Array<String>
    var realIndex10: Array<String>?
    lateinit var mayBeRegex: Array<String>
    lateinit var sixthIndex: Array<String>

    init {
        val iArr: IntArray?
        val iArr2: IntArray?
        val strArr2: Array<String>?
        val strArr3: Array<String>?
        val strArr4: Array<String>?
        if (stringsCountryTsv.size >= 12) {
            val z: Boolean
            val stringThatContainsInt404and405: String = stringsCountryTsv[3]
            val realIndex5: String = stringsCountryTsv[4]
            val realIndex6: String = stringsCountryTsv[5]
            this.country = stringsCountryTsv[1]
            cc = stringsCountryTsv[0]
            countryCallingCodeInt = stringsCountryTsv[2].toInt()
            if (stringThatContainsInt404and405.trim { it <= ' ' }.isEmpty()) {
                iArr = null
            } else {
                val split = TextUtils.split(stringThatContainsInt404and405, ",")
                if (split == null || split.isEmpty()) {
                    iArr = null
                } else {
                    iArr = IntArray(split.size)
                    var i = 0
                    do {
                        iArr[i] = split[i].toInt()
                        i++
                    } while (i < split.size)
                }
            }
            if (iArr != null) {
                contains404and405 = iArr
            }
            if (realIndex5.trim { it <= ' ' }.isEmpty()) {
                iArr2 = null
            } else {
                val split2 = TextUtils.split(realIndex5, ",")
                if (split2 == null || split2.isEmpty()) {
                    iArr2 = null
                } else {
                    iArr2 = IntArray(split2.size)
                    var i2 = 0
                    do {
                        iArr2[i2] = split2[i2].toInt()
                        i2++
                    } while (i2 < split2.size)
                }
            }
            if (iArr2 != null) {
                this.realIndex5 = iArr2
            }
            strArr2 =
                if (realIndex6.trim { it <= ' ' }.isEmpty()) {
                    null
                } else {
                    TextUtils.split(realIndex6, ",")
                }
            if (strArr2 != null) {
                sixthIndex = strArr2
            }
            val mayBeTheOneThatContainsRegex = stringsCountryTsv[7]
            z =
                mayBeTheOneThatContainsRegex.isNotEmpty() || stringsCountryTsv[8].isNotEmpty() || stringsCountryTsv[9].isNotEmpty()
            aBoolean = z
            strArr3 = if (z) {
                TextUtils.split(mayBeTheOneThatContainsRegex, ";")
            } else {
                null
            }
            if (strArr3 != null) {
                mayBeRegex = strArr3
            }
            strArr4 = if (aBoolean) {
                TextUtils.split(stringsCountryTsv[8], ";")
            } else {
                null
            }
            if (strArr4 != null) {
                realIndex9 = strArr4
            }
            realIndex10 = if (aBoolean) TextUtils.split(stringsCountryTsv[9], ";") else null
            realIndex11 = stringsCountryTsv[10]
            realIndex12 = stringsCountryTsv[11]
            realIndex14 = if (stringsCountryTsv.size >= 14) stringsCountryTsv[13] else ""
        } else {
            throw IllegalArgumentException()
        }
    }
}