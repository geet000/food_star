package com.example.foodstarapp.presentation.login

data class LoginState(
    val isLoading: Boolean = false,
    val isUserAuthenticated: Boolean = false,
    val isError: Boolean = false,
    val error: String = "",
    val email: String = "",
    val password: String = ""
)