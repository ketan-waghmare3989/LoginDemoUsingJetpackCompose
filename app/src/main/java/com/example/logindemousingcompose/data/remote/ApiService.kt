package com.example.logindemousingcompose.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("public/login")
    suspend fun login(@Body request: LoginRequest): Response<String>
}