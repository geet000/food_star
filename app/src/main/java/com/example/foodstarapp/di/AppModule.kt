package com.example.foodstarapp.di

import com.example.foodstarapp.data.repository.FirebaseAuthRepo
import com.example.foodstarapp.domain.repository.AuthRepo
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
@Provides
@Singleton
fun provideAuthRepository(auth:FirebaseAuth):AuthRepo{
return FirebaseAuthRepo(auth)
}
}