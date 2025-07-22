package com.sahil.myapp.screens.onboardingScreens.auth.loginPage.repository

import NetworkApiService
import com.google.gson.Gson
import com.sahil.myapp.apiFunc.apiUrls.ApiUrls
import com.sahil.myapp.screens.onboardingScreens.auth.loginPsage.model.LoginResponseModel

class LoginRepository(
    private val networkApiService: NetworkApiService = NetworkApiService()
) {
    suspend fun loginWithPhone(phone: String): LoginResponseModel {
        val url = ApiUrls.loginApi + phone

        val responseString = networkApiService.postApi(
            url = url,
            data = null,
            sendHeader = false,
            headerMap = null
        ) as String

        return Gson().fromJson(responseString, LoginResponseModel::class.java)
    }
}
