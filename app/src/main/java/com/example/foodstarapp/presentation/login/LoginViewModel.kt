package com.example.foodstarapp.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodstarapp.common.Resource
import com.example.foodstarapp.domain.use_case.auth.SignInWithEmailAndPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val signInWithEmailAndPasswordUseCase: SignInWithEmailAndPasswordUseCase) :
    ViewModel() {
    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    fun signInWithEmailAndPassword(onSuccess: () -> Unit) {
        signInWithEmailAndPasswordUseCase(_state.value.email,_state.value.password).onEach { result ->

            when (result) {
                is Resource.Error -> _state.value = LoginState(
                    isUserAuthenticated = false,
                    isError = true,
                    error = result.e?.message ?: ""
                )
                is Resource.Loading -> _state.value = LoginState(
                    isLoading = true,
                    email = _state.value.email,
                    password = _state.value.password
                )
                is Resource.Success -> {
                    _state.value = LoginState(isUserAuthenticated = true)
                    onSuccess()
                }
            }
        }.launchIn(viewModelScope)

    }

    fun updateEmail(email:String) = viewModelScope.launch {
        _state.value = _state.value.copy(email = email)
    }
    fun updatePassword(password:String) = viewModelScope.launch {
        _state.value = _state.value.copy(password = password)
    }


}