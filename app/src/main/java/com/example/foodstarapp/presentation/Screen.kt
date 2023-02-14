package com.example.foodstarapp.presentation

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash")
    object LoginScreen : Screen("login")
    object SignUpScreen : Screen("sign_up")
    object HomeScreen : Screen("home")
}
