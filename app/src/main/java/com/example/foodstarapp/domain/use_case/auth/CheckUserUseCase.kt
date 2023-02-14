package com.example.foodstarapp.domain.use_case.auth

import com.example.foodstarapp.common.Resource
import com.example.foodstarapp.domain.repository.AuthRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CheckUserUseCase @Inject constructor(private val authRepo: AuthRepo ) {
    operator fun invoke(): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        delay(2000)
        emit(authRepo.isAuthenticated())

    }
}