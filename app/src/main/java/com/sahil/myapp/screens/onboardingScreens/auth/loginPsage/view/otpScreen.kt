package com.sahil.myapp.screens.onboardingScreens.auth.loginPsage.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sahil.myapp.ui.components.SpaceSmall
import com.sahil.myapp.ui.theme.AppColor
import com.sahil.myapp.ui.theme.AppTextStyles

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OtpScreen(
    navController : NavController,

) {
    val context = LocalContext.current
    var otp by remember { mutableStateOf("") }
    var scrollState = rememberScrollState()
Scaffold (
    containerColor = AppColor.backgroundColor,
    modifier  = Modifier.fillMaxWidth()
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .imePadding()
            .padding(horizontal = 0.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            "We Have Sent You SMS", style = AppTextStyles.roboto_font25_700_primary,
            textAlign = TextAlign.Center,

            )
        SpaceSmall()
        Text(
            "Enter The OTP We Have Sent To", style = AppTextStyles.roboto_font13_400_tertiary,
            textAlign = TextAlign.Center,
            )
        SpaceSmall()
        Text(
            "+91 - 7871212812", style = AppTextStyles.roboto_font13_400_tertiary,
            textAlign = TextAlign.Center,
            )
    }
}
}


@Preview(showBackground = true)
@Composable
private fun previewOtpScreen() {
    val navController = rememberNavController()
    OtpScreen(navController = navController)
}