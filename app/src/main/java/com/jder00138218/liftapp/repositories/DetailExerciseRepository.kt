package com.jder00138218.liftapp.repositories

import androidx.navigation.NavController
import com.jder00138218.liftapp.network.ApiResponse
import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.network.dto.login.LoginRequest
import com.jder00138218.liftapp.network.services.VerifyDenyExerciseService
import com.jder00138218.liftapp.ui.navigation.Rutas
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

class DetailExerciseRepository(private val api: VerifyDenyExerciseService, private val navController: NavController) {
    suspend fun getDetailExercise(id:Int?): exercise {
        try{
            val exercise: exercise = api.getExcDetail(id)
            return exercise
        } catch (e: SocketTimeoutException) {
            navController.navigate(Rutas.page404.ruta)
            return exercise()
        }
    }

    suspend fun denyExercise(id:Int?): ApiResponse<String> {
        try {
            val response = api.denyExercise(id)
            return ApiResponse.Success(response.toString())
        } catch (e: HttpException) {

            if (e.code() == 410) {
                return ApiResponse.ErrorWithMessage("Eliminado correctamente")
            }
            return ApiResponse.Error(e)
        } catch (e: IOException) {
            return ApiResponse.Error(e)
        }catch (e: SocketTimeoutException) {
            navController.navigate(Rutas.page404.ruta)
            return ApiResponse.Error(e)
        }
    }

    suspend fun verifyExercise(id:Int?): ApiResponse<String> {
        try {
            val response = api.verifyExercise(id)
            return ApiResponse.Success(response.toString())
        } catch (e: HttpException) {

            if (e.code() == 202) {
                return ApiResponse.ErrorWithMessage("Ejercicio Verificado")
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