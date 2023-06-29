package com.jder00138218.liftapp.repositories

import com.jder00138218.liftapp.network.ApiResponse
import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.network.dto.routine.PostRoutineRequest
import com.jder00138218.liftapp.network.dto.routine.routine
import com.jder00138218.liftapp.network.dto.user.PostUserRequest
import com.jder00138218.liftapp.network.services.RoutineService
import retrofit2.HttpException
import java.io.IOException

class RoutineRepository(private val api: RoutineService) {
    suspend fun getMyRoutines(query:String, id:Int?):List<routine>{
        val routines: List<routine> = api.getmyroutines(id,query)
        return routines
    }

    suspend fun createRoutine(difficulty: String,name: String,tag: String,time:String,id:Int?): ApiResponse<String> {
        try {
            val response = api.createRoutine(PostRoutineRequest(difficulty,name,tag,time),id)
            return ApiResponse.Success(response.toString())
        } catch (e: HttpException) {
            if (e.code() == 500) {
                return ApiResponse.ErrorWithMessage("Campos invalidos")
            }
            return ApiResponse.Error(e)
        } catch (e: IOException) {
            return ApiResponse.Error(e)
        }
    }
}