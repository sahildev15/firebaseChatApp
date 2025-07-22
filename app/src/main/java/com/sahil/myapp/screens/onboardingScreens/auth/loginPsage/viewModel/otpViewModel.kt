package com.sahil.myapp.screens.onboardingScreens.auth.loginPsage.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class OtpViewModel : ViewModel() {

    val otpDigits = mutableStateListOf("", "", "", "", "", "")
    val focusedIndex = mutableStateOf(0)

    fun onDigitChange(index: Int, value: String) {
        if (value.length <= 1 && value.all { it.isDigit() }) {
            otpDigits[index] = value
            if (value.isNotEmpty() && index < 5) {
                focusedIndex.value = index + 1
            }
        }
    }

    fun onBackspace(index: Int) {
        if (otpDigits[index].isEmpty() && index > 0) {
            focusedIndex.value = index - 1
            otpDigits[focusedIndex.value] = ""
        } else {
            otpDigits[index] = ""
        }
    }

    fun getOtp(): String {
        return otpDigits.joinToString("")
    }
}
