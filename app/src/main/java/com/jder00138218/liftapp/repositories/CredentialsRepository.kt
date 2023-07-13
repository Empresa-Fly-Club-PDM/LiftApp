package com.jder00138218.liftapp.repositories

import android.util.Log
import androidx.navigation.NavController
import com.jder00138218.liftapp.network.ApiResponse
import com.jder00138218.liftapp.network.dto.login.LoginRequest
import com.jder00138218.liftapp.network.dto.recovery.RecoveryRequest
import com.jder00138218.liftapp.network.dto.register.RegisterRequest
import com.jder00138218.liftapp.network.retrofit.RetrofitInstance
import com.jder00138218.liftapp.network.services.AuthService
import com.jder00138218.liftapp.ui.navigation.Rutas
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

class CredentialsRepository(private val api: AuthService, private val navController: NavController) {


    suspend fun login(email: String, password: String): ApiResponse<String> {
        try {
            val response = api.login(LoginRequest(email, password))
            return ApiResponse.Success(response.accesToken)
        } catch (e: HttpException) {
            if (e.code() == 401) {
                return ApiResponse.ErrorWithMessage("Invalid email or password")
            }
            return ApiResponse.Error(e)
        } catch (e: IOException) {
            return ApiResponse.Error(e)
        }catch (e: SocketTimeoutException) {
            navController.navigate(Rutas.page404.ruta)
            return ApiResponse.Error(e)
        }
    }

    suspend fun register(
        name: String,
        email: String,
        password: String, genre: String,
        date: String,
        weigth: Int,
        height: Int,
    ): ApiResponse<String> {
        try {
            val response =
                api.register(RegisterRequest(name, email, password, genre, weigth, height, date))
            return ApiResponse.Success("Created")
        } catch (e: HttpException) {
            if (e.code() == 400) {
                return ApiResponse.ErrorWithMessage("Invalid information")
            }
            return ApiResponse.Error(e)
        } catch (e: IOException) {
            return ApiResponse.Error(e)
        }catch (e: SocketTimeoutException) {
            navController.navigate(Rutas.page404.ruta)
            return ApiResponse.Error(e)
        }
    }

    suspend fun recovery(email: String): ApiResponse<String> {
        try {
            val response = api.recovery(RecoveryRequest(email))
            return ApiResponse.Success("Done")
        } catch (e: HttpException) {
            if (e.code() == 406) {
                return ApiResponse.ErrorWithMessage("Campo Invalido")
            }
            return ApiResponse.Error(e)
        } catch (e: IOException) {
            return ApiResponse.Error(e)
        }catch (e: SocketTimeoutException) {
            navController.navigate(Rutas.page404.ruta)
            return ApiResponse.Error(e)
        }

    }
}