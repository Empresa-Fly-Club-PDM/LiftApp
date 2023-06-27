package com.jder00138218.liftapp.network.services

import com.jder00138218.liftapp.network.dto.exercise.PostVerifiedExerciseRequest
import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.network.dto.login.LoginRequest
import com.jder00138218.liftapp.network.dto.login.LoginResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ExerciseService {
    @GET("ejercicio/get/admin/earring")
    suspend fun getSolicitudes(@Query("query") query:String): List<exercise>

    @GET("ejercicio/get/admin/verified")
    suspend fun getVerified(@Query("query") query:String): List<exercise>

    @POST("ejercicio/post/admin/{id}")
    suspend fun createExercise(@Body credentials: PostVerifiedExerciseRequest,@Path("id") id: Int?): Response<Void>
}