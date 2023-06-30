package com.jder00138218.liftapp.repositories

import com.jder00138218.liftapp.network.ApiResponse
import com.jder00138218.liftapp.network.dto.exercise.PostVerifiedExerciseRequest
import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.network.dto.user.PostUserRequest
import com.jder00138218.liftapp.network.dto.user.user
import com.jder00138218.liftapp.network.services.UserService
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException

class UserRepository (private val api: UserService){
    suspend fun getAllAdmins(query:String):List<user>{
        val users: List<user> = api.getAllAdmins(query)
        return users
    }

    suspend fun searchForfriend(query:String):List<user>{
        val users: List<user> = api.searchForFriends(query)
        return users
    }

    suspend fun getMyFriends(id:Int?):List<user>{
        val users: List<user> = api.getMyFriends(id)
        return users
    }
    suspend fun getUserDetails(id:Int?):user{
        val userdetails: user = api.getUserDetails(id)
        return userdetails
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
        }
    }
}