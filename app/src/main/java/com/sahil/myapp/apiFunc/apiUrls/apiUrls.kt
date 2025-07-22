package com.sahil.myapp.apiFunc.apiUrls

object ApiUrls {
    var baseUrl = "https://dev-adgen.gimbooks.com"
    var loginApi = "$baseUrl/onboarding/login/otp?mobile="
    var otpApi = "$baseUrl/onboarding/verify/otp?username="
    var createCompany = "$baseUrl/company/"
    var selectCompany = "$baseUrl/company/"
    var getCompanySectors = "$baseUrl/utils/company-sectors"
    var userVerification = "$baseUrl/user/verify"
    var companyProfile = "$baseUrl/user/me"
    var carouselSlider = "$baseUrl/carousel/"
    var categoryList = "$baseUrl/categories/?featured=true"
    var upcomingCategory = "$baseUrl/categories/?"
    var chatHistory = "$baseUrl/chat/history"
    var templates = "$baseUrl/templates/?"
    var updateProfile = "$baseUrl/company/"
}
