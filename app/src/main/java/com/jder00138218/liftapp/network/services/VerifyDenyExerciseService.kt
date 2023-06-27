package com.jder00138218.liftapp.network.services

import com.jder00138218.liftapp.network.dto.exercise.exercise
import retrofit2.http.GET
import retrofit2.http.Path

interface VerifyDenyExerciseService {
    @GET("ejercicio/details/{id}")
    suspend fun getExcDetail(@Path("id") id: Int?):exercise
}