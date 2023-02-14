package com.example.foodstarapp.presentation.splash

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodstarapp.common.Resource
import com.example.foodstarapp.domain.use_case.auth.CheckUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val checkUserUseCase: CheckUserUseCase) :
    ViewModel() {
    private val _state = mutableStateOf(SplashState())
    val state: State<SplashState> = _state



    fun initApp(onSuccess: () -> Unit,onFailure: ()-> Unit) {
        checkUserUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = SplashState(isLoading = false, isUserAuthenticated = true)
                    onSuccess()

                }
                is Resource.Error -> {
                    _state.value = SplashState(isLoading = false, isUserAuthenticated = false)
                    onFailure()
                }
                is Resource.Loading -> {
                    _state.value = SplashState(isLoading = true)
                }

            }


        }.launchIn(viewModelScope)
    }


}