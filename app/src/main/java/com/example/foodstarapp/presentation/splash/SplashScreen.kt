package com.example.foodstarapp.presentation.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.foodstarapp.presentation.Screen

@Composable
fun SplashScreen(navController: NavController, viewModel: SplashViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = if (state.isLoading) "loading" else if (state.isUserAuthenticated) "true" else "false")

        LaunchedEffect(key1 = true) {
            viewModel.initApp(onSuccess = {
                navController.navigate(Screen.HomeScreen.route) {
                    launchSingleTop=true
                    popUpTo(Screen.SplashScreen.route) { inclusive = true }
                }
            }, onFailure = {
                navController.navigate(Screen.LoginScreen.route) {
                    launchSingleTop=true
                    popUpTo(Screen.SplashScreen.route) { inclusive = true }
                }
            })
        }

    }


}