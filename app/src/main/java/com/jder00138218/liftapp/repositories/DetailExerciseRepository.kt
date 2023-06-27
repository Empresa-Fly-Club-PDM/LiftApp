package com.jder00138218.liftapp.repositories

import com.jder00138218.liftapp.network.ApiResponse
import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.network.dto.login.LoginRequest
import com.jder00138218.liftapp.network.services.VerifyDenyExerciseService
import retrofit2.HttpException
import java.io.IOException

class DetailExerciseRepository(private val api: VerifyDenyExerciseService) {
    suspend fun getDetailExercise(id:Int?): exercise {
        val exercise: exercise = api.getExcDetail(id)
        return exercise
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
        }
    }
}