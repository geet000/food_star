package com.example.foodstarapp.domain.use_case.auth

import com.example.foodstarapp.common.Resource
import com.example.foodstarapp.domain.repository.AuthRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignInWithEmailAndPasswordUseCase @Inject constructor(private val authRepo: AuthRepo) {
    operator fun invoke(email: String, password: String): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            if(email.isBlank()){
                emit(Resource.Error(Exception("Please enter email")))
            }else if(password.isBlank()){
                emit(Resource.Error(Exception("Please enter password")))

            }else if(password.length < 6){
                emit(Resource.Error(Exception("Password must be at least six characters long")))
            }else{
                authRepo.signInWithEmailAndPassword(email,password)
                emit(Resource.Success(true))
            }

        } catch (e: Exception) {
            emit(Resource.Error<Boolean>(e))

        }
    }
}