package com.example.logindemousingcompose.domain.usecase

import android.util.Log
import com.example.logindemousingcompose.data.remote.LoginResponse
import com.example.logindemousingcompose.domain.repository.AuthRepository
import retrofit2.Response

class LoginUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(username: String, password: String): Response<String> {
        return repository.login(username, password)
    }
}