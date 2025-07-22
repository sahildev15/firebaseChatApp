// LoginScreen.kt
package com.sahil.myapp.screens.onboardingScreens.auth.loginPage.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.*
import com.sahil.myapp.ui.components.CommonButton
import com.sahil.myapp.ui.components.CommonTextField
import com.sahil.myapp.ui.components.SpaceLarge
import com.sahil.myapp.ui.components.SpaceMedium
import com.sahil.myapp.ui.components.SpaceSmall
import com.sahil.myapp.ui.theme.AppColor
import com.sahil.myapp.ui.theme.AppTextStyles

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    navController: NavController,
//    viewModel: LoginViewModel
) {
    val context = LocalContext.current
    var phoneNumber by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    val composition by rememberLottieComposition(
        LottieCompositionSpec.Asset("Chat.json")
    )

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )

    Scaffold(
        containerColor = AppColor.backgroundColor,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .safeContentPadding()
                .verticalScroll(scrollState)
                .imePadding()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(60.dp))

            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier
                    .height(400.dp)
                    .fillMaxWidth()
            )
            Spacer(Modifier.height(90.dp))

            Text(
                text = "Login To Start Chatting",
                style = AppTextStyles.roboto_font25_700_primary
            )
            SpaceMedium()
            Text(
                text = "Find your all friend in one place by",
                style = AppTextStyles.roboto_font13_400_tertiary
            )
            SpaceSmall()
            Text(
                text = "Signing the app quick and start your chit-chat",
                style = AppTextStyles.roboto_font13_400_tertiary
            )


            Spacer(modifier = Modifier.height(30.dp))

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
            Spacer(modifier = Modifier.height(30.dp))
            CommonButton(
                onClick = {
//                    viewModel.login(phoneNumber)
                },
                isLoading = false,
                isButtonEnabled = true,
                title = "Get Otp",
            )

            Spacer(modifier = Modifier.height(16.dp))

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


@Preview(showBackground = true)
@Composable
private fun PreviewLogin() {
    val navController = rememberNavController()
    LoginScreen(navController = navController)
}
