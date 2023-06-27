package com.jder00138218.liftapp.network.services

import com.jder00138218.liftapp.network.dto.exercise.exercise
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ExerciseService {
    @GET("ejercicio/get/admin/earring")
    suspend fun getSolicitudes(@Query("query") query:String): List<exercise>

}