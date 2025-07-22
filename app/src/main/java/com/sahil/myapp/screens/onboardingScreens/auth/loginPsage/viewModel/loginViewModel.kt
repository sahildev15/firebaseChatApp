package com.sahil.myapp.screens.onboardingScreens.auth.loginPage.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sahil.myapp.screens.onboardingScreens.auth.loginPage.repository.LoginRepository
import com.sahil.myapp.screens.onboardingScreens.auth.loginPsage.model.LoginResponseModel
import kotlinx.coroutines.launch


class LoginViewModel(
    private val repository: LoginRepository
) : ViewModel() {

    var isLoading = mutableStateOf(false)
    var isSuccess = mutableStateOf(false)
    var errorMessage = mutableStateOf("")

    fun login(phone: String) {
        isLoading.value = true
        errorMessage.value = ""
        isSuccess.value = false

        viewModelScope.launch {
            try {
                val response = repository.loginWithPhone(phone)
                println("✅ API Response Model: $response") // ViewModel logging

                if (response.message != "") {
                    isSuccess.value = true
                } else {
                    errorMessage.value = response.message ?: "Something went wrong"
                }
            } catch (e: Exception) {
                println("❌ API Error: ${e.message}") // Error logging
                errorMessage.value = e.localizedMessage ?: "Network error"
            } finally {
                isLoading.value = false
            }
        }
    }
}

class LoginViewModelFactory(
    private val repository: LoginRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(repository) as T
    }
}
