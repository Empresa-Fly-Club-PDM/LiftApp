package com.jder00138218.liftapp.network.services

import com.jder00138218.liftapp.network.dto.exercise.PostVerifiedExerciseRequest
import com.jder00138218.liftapp.network.dto.lift.lift
import com.jder00138218.liftapp.network.dto.routine.PostRoutineRequest
import com.jder00138218.liftapp.network.dto.routine.routine
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RoutineService {
    @GET("routine/myRoutines/{id}")
    suspend fun getmyroutines(@Path("id") id:Int?,@Query("query") query:String): List<routine>

    @POST("routine/add/{id}")
    suspend fun createRoutine(@Body newRoutine: PostRoutineRequest, @Path("id") id:Int?): Response<Void>
}