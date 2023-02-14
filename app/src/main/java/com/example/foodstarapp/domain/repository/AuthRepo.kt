package com.example.foodstarapp.domain.repository

import com.example.foodstarapp.common.Resource
import com.example.foodstarapp.domain.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

interface AuthRepo {
    val currentUser: User?
    suspend fun isAuthenticated() : Resource<Boolean>

    fun getAuthState(viewModelScope: CoroutineScope): StateFlow<Boolean>

    suspend fun signUpWithEmailAndPassword(email: String, password: String): Resource<Boolean>

    suspend fun signInWithEmailAndPassword(email: String, password: String): Resource<Boolean>


}