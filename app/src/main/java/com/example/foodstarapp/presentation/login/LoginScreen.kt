package com.example.foodstarapp.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {
    val state = viewModel.state
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            value = state.value.email,
            label = { Text("Email") },
            onValueChange = { viewModel.updateEmail(it) })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = state.value.password,
            label = { Text("Password") },
            onValueChange = { viewModel.updatePassword(it) })
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                focusManager.clearFocus()
                viewModel.signInWithEmailAndPassword {

                }

            }
        ) {
            Text(
                text = "Sign In",
                fontSize = 15.sp
            )
        }
    }

}