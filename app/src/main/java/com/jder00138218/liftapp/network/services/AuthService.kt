package com.jder00138218.liftapp.network.services

import com.jder00138218.liftapp.network.dto.login.LoginRequest
import com.jder00138218.liftapp.network.dto.login.LoginResponse
import com.jder00138218.liftapp.network.dto.register.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST

/*
* proporciona una abstracción de las operaciones de autenticación en la API
*/
interface AuthService {
    @POST("auth/login")
    suspend fun login(@Body credentials: LoginRequest): LoginResponse

    @POST("auth/register")
    suspend fun register(@Body credentials: RegisterRequest)
}