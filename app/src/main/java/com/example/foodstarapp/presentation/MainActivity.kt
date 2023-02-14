package com.example.foodstarapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodstarapp.presentation.home.HomeScreen
import com.example.foodstarapp.presentation.login.LoginScreen
import com.example.foodstarapp.presentation.splash.SplashScreen
import com.example.foodstarapp.presentation.ui.theme.FoodStarAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodStarAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.SplashScreen.route){
                        composable(route = Screen.SplashScreen.route){
                            SplashScreen(navController)
                        }
                        composable(route = Screen.LoginScreen.route){
                            LoginScreen(navController)
                        }
                        composable(route = Screen.HomeScreen.route){
                            HomeScreen()
                        }

                    }
                }
            }
        }
    }

}
