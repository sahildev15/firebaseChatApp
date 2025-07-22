package com.sahil.myapp.routes

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sahil.myapp.screens.onboardingScreens.auth.loginPage.repository.LoginRepository
import com.sahil.myapp.screens.onboardingScreens.auth.loginPage.view.LoginScreen
import com.sahil.myapp.screens.onboardingScreens.auth.loginPage.viewmodel.LoginViewModel
import com.sahil.myapp.screens.onboardingScreens.auth.loginPage.viewmodel.LoginViewModelFactory
import com.sahil.myapp.screens.onboardingScreens.splashScreen.view.SplashScreenUi

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.SPLASH_SCREEN) {
        composable(Routes.SPLASH_SCREEN) {
            SplashScreenUi(navController)
        }
        composable(Routes.LOGIN) {
            val loginViewModel: LoginViewModel = viewModel(
                factory = LoginViewModelFactory(LoginRepository())
            )
            LoginScreen(navController = navController, )
        }
    }
}
