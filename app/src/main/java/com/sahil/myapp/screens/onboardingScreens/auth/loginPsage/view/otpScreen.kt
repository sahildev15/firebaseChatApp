package com.sahil.myapp.screens.onboardingScreens.auth.loginPsage.view

import android.annotation.SuppressLint
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sahil.myapp.screens.onboardingScreens.auth.loginPsage.viewmodel.OtpViewModel
import com.sahil.myapp.ui.components.SpaceSmall
import com.sahil.myapp.ui.theme.AppColor
import com.sahil.myapp.ui.theme.AppTextStyles
import kotlin.coroutines.ContinuationInterceptor
import androidx.compose.ui.input.key.Key
import com.sahil.myapp.ui.components.CommonButton
import com.sahil.myapp.ui.components.SpaceLarge


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OtpScreen(navController: NavController, viewModel: OtpViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {

    val scrollState = rememberScrollState()
    val focusRequesters = List(6) { remember { FocusRequester() } }

    // Automatically focus first field
    LaunchedEffect(Unit) {
        focusRequesters[0].requestFocus()
    }

    Scaffold(
        containerColor = AppColor.backgroundColor,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .imePadding()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                "We Have Sent You SMS",
                style = AppTextStyles.roboto_font25_700_primary,
                textAlign = TextAlign.Center
            )
            SpaceSmall()
            Text(
                "Enter The OTP We Have Sent To",
                style = AppTextStyles.roboto_font13_400_tertiary,
                textAlign = TextAlign.Center
            )
            SpaceSmall()
            Text(
                "+91 - 7871212812",
                style = AppTextStyles.roboto_font13_400_tertiary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(6) { index ->
                    OtpTextField(
                        value = viewModel.otpDigits[index],
                        onValueChange = { viewModel.onDigitChange(index, it) },
                        onBackspace = { viewModel.onBackspace(index) },
                        modifier = Modifier
                            .weight(1f)
                            .height(60.dp),
                        isFocused = viewModel.focusedIndex.value == index,
                        focusRequester = focusRequesters[index]
                    )
                }
            }
            SpaceLarge()
            CommonButton(
                "Proceed",
                isLoading = false,
                isButtonEnabled = true,
                onClick = {}
            )
        }
    }
}

@Composable
fun OtpTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onBackspace: () -> Unit,
    modifier: Modifier = Modifier,
    isFocused: Boolean = false,
    focusRequester: FocusRequester
) {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    OutlinedTextField(
        value = value,
        onValueChange = {
            if (it.length <= 1) onValueChange(it)
        },
        modifier = modifier
            .focusRequester(focusRequester)
            .onKeyEvent {
                if (it.type == KeyEventType.KeyDown && it.key == Key.Backspace) {
                    onBackspace()
                    true
                } else {
                    false
                }
            },

                textStyle = AppTextStyles.roboto_font25_700_primary.copy(textAlign = TextAlign.Center),
        singleLine = true,
        interactionSource = interactionSource,

    )

    // Request focus if it's the focused index
    LaunchedEffect(isFocused) {
        if (isFocused) {
            focusRequester.requestFocus()
        }
    }
}

@Preview
@Composable
private fun preview() {
    var navController= rememberNavController()
    OtpScreen(navController)
}