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

    suspend fun searchExerciseDatabase(query:String, id:Int?):List<exercise>{
        val exercises: List<exercise> = api.searchExerciseDatabase(id,query)
        return exercises
    }

    suspend fun getRoutineDetail(id:Int?):List<exercise>{
        val exercises: List<exercise> = api.getRoutineDetail(id)
        return exercises
    }

    suspend fun removExercise(idexc:Int?,idrut:Int?): ApiResponse<String> {
        try {
            val response = api.removeExerciseFromRoutine(idexc,idrut)
            return ApiResponse.Success(response.toString())
        } catch (e: HttpException) {

            if (e.code() == 202) {
                return ApiResponse.ErrorWithMessage("Ejercicio Removido")
            }
            return ApiResponse.Error(e)
        } catch (e: IOException) {
            return ApiResponse.Error(e)
        }
    }
    suspend fun addExercise(idrut:Int?,idexc:Int?): ApiResponse<String> {
        try {
            val response = api.addExerciseToRoutine(idrut,idexc)
            return ApiResponse.Success(response.toString())
        } catch (e: HttpException) {

            if (e.code() == 202) {
                return ApiResponse.ErrorWithMessage("Ejercicio Removido")
            }
            return ApiResponse.Error(e)
        } catch (e: IOException) {
            return ApiResponse.Error(e)
        }
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

    suspend fun deleteRoutine(id:Int?): ApiResponse<String> {
        try {
            val response = api.deleteRoutine(id)
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