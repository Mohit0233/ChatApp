package com.example.chatapp.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SimData(
    val number: String,
    val carrierName: String,
    val countryIso: String,
    var isSelected: Boolean
) : Parcelable