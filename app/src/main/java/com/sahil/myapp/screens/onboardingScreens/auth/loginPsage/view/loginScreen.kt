// LoginScreen.kt
package com.sahil.myapp.screens.onboardingScreens.auth.loginPage.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sahil.myapp.R
import com.sahil.myapp.screens.onboardingScreens.auth.loginPage.viewmodel.LoginViewModel
import com.sahil.myapp.ui.components.CommonButton
import com.sahil.myapp.ui.components.CommonTextField
import com.sahil.myapp.ui.theme.AppColor
import com.sahil.myapp.ui.theme.AppTextStyles

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel
) {
    val context = LocalContext.current
    var phoneNumber by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    val isLoading by viewModel.isLoading
    val isSuccess by viewModel.isSuccess
    val errorMessage by viewModel.errorMessage

    Scaffold(
        containerColor = AppColor.backgroundColor,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .safeContentPadding()
                .verticalScroll(scrollState)
                .imePadding()
                .padding(horizontal = 0.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = R.drawable.onboarding),
                contentDescription = "Onboarding image",
                modifier = Modifier
                    .height(500.dp)
                    .width(500.dp)
            )

            Text(
                text = "Login To Create Poster",
                style = AppTextStyles.roboto_font25_700_primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            CommonTextField(
                hint = "Enter Your Mobile Number",
                label = "phone number",
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                isRequired = true,
                showValidationError = false,
                keyboardType = KeyboardType.Number,
                maxLength = 10,
                isPassword = false,
                prefixIcon = { Text("+91") }
            )

            Spacer(modifier = Modifier.height(16.dp))
            CommonButton(
                onClick = { viewModel.login(phoneNumber) },
                isLoading = isLoading,
                isButtonEnabled = phoneNumber.length == 10,
                title = "Get Otp",
            )
            Spacer(modifier = Modifier.height(16.dp))

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    style = AppTextStyles.inter_font12_400_secondary
                )
            }

            Text(
                text = "By Continuing, You Agree To Our ",
                style = AppTextStyles.inter_font12_400_secondary,
                textAlign = TextAlign.Center
            )

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Terms of Service",
                    color = AppColor.violet,
                    textDecoration = TextDecoration.Underline
                )
                Text(
                    text = " and ",
                    style = AppTextStyles.inter_font12_400_secondary
                )
                Text(
                    text = "Privacy Policy",
                    color = AppColor.violet,
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}
