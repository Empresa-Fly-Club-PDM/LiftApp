package com.jder00138218.liftapp.repositories

import androidx.navigation.NavController
import com.jder00138218.liftapp.network.ApiResponse
import com.jder00138218.liftapp.network.dto.exercise.PostVerifiedExerciseRequest
import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.network.dto.register.RegisterRequest
import com.jder00138218.liftapp.network.dto.user.PostUserRequest
import com.jder00138218.liftapp.network.dto.user.user
import com.jder00138218.liftapp.network.services.UserService
import com.jder00138218.liftapp.ui.navigation.Rutas
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

class UserRepository (private val api: UserService, private val navController: NavController){
    suspend fun getAllAdmins(query:String):List<user>{
        try{
            val users: List<user> = api.getAllAdmins(query)
            return users
        }catch (e: SocketTimeoutException) {
            navController.navigate(Rutas.page404.ruta)
            return emptyList()
        }

    }
    suspend fun getRanking(query:String):List<user>{
        try{
            val users: List<user> = api.getRanking(query)
            return users
        }catch (e: SocketTimeoutException) {
            navController.navigate(Rutas.page404.ruta)
            return emptyList()
        }
    }

    suspend fun searchForfriend(id:Int?, query:String):List<user>{
        try{
            val users: List<user> = api.searchForFriends(id,query)
            return users
        }catch (e: SocketTimeoutException) {
            navController.navigate(Rutas.page404.ruta)
            return emptyList()
        }

    }

    suspend fun getMyFriends(id:Int?):List<user>{
        try{
            val users: List<user> = api.getMyFriends(id)
            return users
        }catch (e: SocketTimeoutException) {
            navController.navigate(Rutas.page404.ruta)
            return emptyList()
        }

    }
    suspend fun getUserDetails(id:Int?):user{
        try{
            val userdetails: user = api.getUserDetails(id)
            return userdetails
        }catch (e: SocketTimeoutException) {
            navController.navigate(Rutas.page404.ruta)
            return user()
        }

    }

    suspend fun deleteUser(id:Int?): ApiResponse<String> {
        try {
            val response = api.deleteUser(id)
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

    suspend fun addFriend(current:Int?,friend:Int?): ApiResponse<String> {
        try {
            val response = api.addFriend(current,friend)
            return ApiResponse.Success(response.toString())
        } catch (e: HttpException) {

            if (e.code() == 410) {
                return ApiResponse.ErrorWithMessage("Amigo agregado")
            }
            return ApiResponse.Error(e)
        } catch (e: IOException) {
            return ApiResponse.Error(e)
        }catch (e: SocketTimeoutException) {
            navController.navigate(Rutas.page404.ruta)
            return ApiResponse.Error(e)
        }
    }
    suspend fun createUser(nombrecompleto: String,email: String,password: String): ApiResponse<String> {
        try {
            val response = api.createUser(PostUserRequest(nombrecompleto,email,password))
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
    suspend fun editUser(nombrecompleto: String,email: String,password: String,id: Int?): ApiResponse<String> {
        try {
            val response = api.editUser(PostUserRequest(nombrecompleto,email,password),id)
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

    suspend fun editClient(nombrecompleto: String,email: String,password: String,genero:String,fechanac:String,weight:Int,height:Int,id: Int?): ApiResponse<String> {
        try {
            val response = api.editMyprofile(RegisterRequest(nombrecompleto,email,password,genero,weight,height,fechanac),id)
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