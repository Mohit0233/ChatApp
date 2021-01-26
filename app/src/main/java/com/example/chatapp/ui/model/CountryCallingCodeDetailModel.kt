package com.example.chatapp.ui.model

import android.graphics.drawable.Drawable

data class CountryCallingCodeDetailModel(
    val ccc: String,
    val countryFirstName: String,
    val countrySecondName: String,
    var countryFlag: Drawable,
    var isSelected: Boolean
)