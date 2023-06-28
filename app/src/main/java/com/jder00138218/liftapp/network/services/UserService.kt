package com.jder00138218.liftapp.network.services

import com.jder00138218.liftapp.network.dto.exercise.PostVerifiedExerciseRequest
import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.network.dto.user.PostUserRequest
import com.jder00138218.liftapp.network.dto.user.user
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    @GET("usuario/get/admins")
    suspend fun getAllAdmins(@Query("query") query:String): List<user>
    @GET("usuario/details/{id}")
    suspend fun getUserDetails(@Path("id") id:Int?): user
    @POST("usuario/add/admin")
    suspend fun createUser(@Body newUser: PostUserRequest): Response<Void>
}