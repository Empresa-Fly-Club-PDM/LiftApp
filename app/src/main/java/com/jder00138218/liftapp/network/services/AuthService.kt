package com.jder00138218.liftapp.network.services

import com.jder00138218.liftapp.network.dto.login.LoginRequest
import com.jder00138218.liftapp.network.dto.login.LoginResponse
import com.jder00138218.liftapp.network.dto.recovery.RecoveryRequest
import com.jder00138218.liftapp.network.dto.recovery.RecoveryResponse
import com.jder00138218.liftapp.network.dto.register.RegisterRequest
import retrofit2.Response
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

/*
* proporciona una abstracción de las operaciones de autenticación en la API
*/
interface AuthService {
    @POST("auth/login")
    suspend fun login(@Body credentials: LoginRequest): LoginResponse

    @POST("auth/register")
    suspend fun register(@Body credentials: RegisterRequest)


    @POST("auth/recoverpassword")
    suspend fun recovery(@Body request: RecoveryRequest): Response<ResponseBody>

}