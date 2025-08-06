package com.example.logindemousingcompose.data.repository

import com.example.logindemousingcompose.data.remote.ApiService
import com.example.logindemousingcompose.data.remote.LoginRequest
import com.example.logindemousingcompose.domain.repository.AuthRepository
import retrofit2.Response

class AuthRepositoryImpl(private val apiService: ApiService) : AuthRepository {
    override suspend fun login(userName: String, password: String): Response<String> {
        return apiService.login(LoginRequest(userName,password))
    }
}