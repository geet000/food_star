package com.example.foodstarapp.data.repository

import com.example.foodstarapp.common.Resource
import com.example.foodstarapp.domain.model.User
import com.example.foodstarapp.domain.repository.AuthRepo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthRepo @Inject constructor(private val auth: FirebaseAuth) :
    AuthRepo {
    override val currentUser = auth.currentUser?.let {
        User(
            uId = it.uid,
            name = it.displayName ?: "",
            email = it.email ?: "",
            phoneNumber = it.phoneNumber ?: "",
            photoUrl = it.photoUrl?.toString() ?: ""
        )
    }

    override suspend fun isAuthenticated()= auth.currentUser?.let { Resource.Success(true) } ?: Resource.Error(Exception())

    override fun getAuthState(viewModelScope: CoroutineScope) = callbackFlow {
        val authStateListener = AuthStateListener { auth ->
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }.stateIn(MainScope(), SharingStarted.WhileSubscribed(), auth.currentUser == null)

    override suspend fun signUpWithEmailAndPassword(
        email: String,
        password: String
    ): Resource<Boolean> {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(e)

        }
    }


    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Resource<Boolean> = try {
        auth.signInWithEmailAndPassword(email, password).await()
        Resource.Success(true)


    } catch (e: Exception) {
        Resource.Error(e)

    }


}