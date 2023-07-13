package com.jder00138218.liftapp.repositories

import androidx.navigation.NavController
import com.jder00138218.liftapp.network.ApiResponse
import com.jder00138218.liftapp.network.dto.exercise.PostVerifiedExerciseRequest
import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.network.dto.login.LoginRequest
import com.jder00138218.liftapp.network.services.ExerciseService
import com.jder00138218.liftapp.ui.navigation.Rutas
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

class ExerciseRepository(private val api:ExerciseService,private val navController: NavController) {
    suspend fun getSolicitudes(query:String):List<exercise>{
        try{
            val exercises: List<exercise> = api.getSolicitudes(query)
            return exercises
        }catch (e: SocketTimeoutException) {
            navController.navigate(Rutas.page404.ruta)
            return emptyList()
        }
    }

    suspend fun getPersonalExercises(id:Int?,query:String):List<exercise>{
        try{
            val exercises: List<exercise> = api.getPersonalExercises(id,query)
            return exercises
        }catch (e: SocketTimeoutException) {
            navController.navigate(Rutas.page404.ruta)
            return emptyList()
        }
    }
    suspend fun getVerified(query:String):List<exercise> {
        try {
            val exercises: List<exercise> = api.getVerified(query)
            return exercises
        }catch (e: SocketTimeoutException) {
            navController.navigate(Rutas.page404.ruta)
            return emptyList()
        }
    }


    suspend fun getDetailExercise(id:Int?): exercise {
        try{
            val exercise: exercise = api.getExcDetail(id)
            return exercise
        }catch (e: SocketTimeoutException) {
            navController.navigate(Rutas.page404.ruta)
            return exercise()
        }

    }

    suspend fun deleteExercise(id:Int?): ApiResponse<String> {
        try {
            val response = api.deleteExercise(id)
            return ApiResponse.Success("Done")
        } catch (e: HttpException) {
            if (e.code() == 500) {
                return ApiResponse.ErrorWithMessage("El ejercicio está siendo utilizado")
            }
            return ApiResponse.Error(e)
        } catch (e: IOException) {
            return ApiResponse.Error(e)
        }catch (e: SocketTimeoutException) {
            navController.navigate(Rutas.page404.ruta)
            return ApiResponse.Error(e)
        }
    }
    suspend fun createExercise(description: String,difficulty: String,muscle: String,name: String,reps: Int,sets: Int,type: String,id:Int?): ApiResponse<String> {
        try {
            val response = api.createExercise(PostVerifiedExerciseRequest(description,difficulty,muscle,name,reps,sets,type),id)
            return ApiResponse.Success(response.toString())
        } catch (e: HttpException) {

            if (e.code() == 500) {
                return ApiResponse.ErrorWithMessage("Invalid Fields")
            }
            return ApiResponse.Error(e)
        } catch (e: IOException) {
            return ApiResponse.Error(e)
        }catch (e: SocketTimeoutException) {
            navController.navigate(Rutas.page404.ruta)
            return ApiResponse.Error(e)
        }
    }

    suspend fun createPersonal(description: String,difficulty: String,muscle: String,name: String,reps: Int,sets: Int,type: String,id:Int?): ApiResponse<String> {
        try {
            val response = api.createPersonalExercise(PostVerifiedExerciseRequest(description,difficulty,muscle,name,reps,sets,type),id)
            return ApiResponse.Success(response.toString())
        } catch (e: HttpException) {

            if (e.code() == 500) {
                return ApiResponse.ErrorWithMessage("Invalid Fields")
            }
            return ApiResponse.Error(e)
        } catch (e: IOException) {
            return ApiResponse.Error(e)
        }catch (e: SocketTimeoutException) {
            navController.navigate(Rutas.page404.ruta)
            return ApiResponse.Error(e)
        }
    }

    suspend fun verify(description: String,difficulty: String,muscle: String,name: String,reps: Int,sets: Int,type: String,id:Int?): ApiResponse<String> {
        try {
            val response = api.solVerifictaion(PostVerifiedExerciseRequest(description,difficulty,muscle,name,reps,sets,type),id)
            return ApiResponse.Success(response.toString())
        } catch (e: HttpException) {

            if (e.code() == 500) {
                return ApiResponse.ErrorWithMessage("Invalid Fields")
            }
            return ApiResponse.Error(e)
        } catch (e: IOException) {
            return ApiResponse.Error(e)
        }catch (e: SocketTimeoutException) {
            navController.navigate(Rutas.page404.ruta)
            return ApiResponse.Error(e)
        }
    }

    suspend fun editExercise(description: String,difficulty: String,muscle: String,name: String,reps: Int,sets: Int,type: String,id:Int?): ApiResponse<String> {
        try {
            val response = api.editExercise(PostVerifiedExerciseRequest(description,difficulty,muscle,name,reps,sets,type),id)
            return ApiResponse.Success(response.toString())
        } catch (e: HttpException) {

            if (e.code() == 500) {
                return ApiResponse.ErrorWithMessage("Campos invalidos")
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