package com.sahil.myapp.screens.onboardingScreens.splashScreen.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.sahil.myapp.routes.Routes
import kotlinx.coroutines.delay

@Composable
fun SplashScreenUi(navController: NavController) {
    LaunchedEffect(true) {
        delay(2000)
        navController.navigate(Routes.LOGIN) {
            popUpTo(Routes.SPLASH_SCREEN) { inclusive = true }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "MyApp",
            style = MaterialTheme.typography.headlineLarge
        )
    }
}
