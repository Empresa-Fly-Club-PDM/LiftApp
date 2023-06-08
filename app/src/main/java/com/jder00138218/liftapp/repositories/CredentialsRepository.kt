package com.jder00138218.liftapp.repositories

import com.jder00138218.liftapp.network.ApiResponse
import com.jder00138218.liftapp.network.dto.login.LoginRequest
import com.jder00138218.liftapp.network.services.AuthService
import retrofit2.HttpException
import java.io.IOException

class CredentialsRepository(private val api : AuthService) {


    suspend fun login(email: String, password: String): ApiResponse<String>{
        try {

            val response = api.login(LoginRequest(email,password))
            return ApiResponse.Success(response.accesToken)

        }catch (e: HttpException){
            if(e.code() == 400){
                return ApiResponse.ErrorWithMessage("Invalid email or password")
            }
            return ApiResponse.Error(e)
        }catch (e: IOException){
            return ApiResponse.Error(e)
        }
    }
}