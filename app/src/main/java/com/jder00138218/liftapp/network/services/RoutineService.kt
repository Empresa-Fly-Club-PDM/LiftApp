package com.jder00138218.liftapp.network.services

import com.jder00138218.liftapp.network.dto.exercise.PostVerifiedExerciseRequest
import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.network.dto.lift.lift
import com.jder00138218.liftapp.network.dto.routine.PostRoutineRequest
import com.jder00138218.liftapp.network.dto.routine.routine
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface RoutineService {
    @GET("routine/myRoutines/{id}")
    suspend fun getmyroutines(@Path("id") id:Int?,@Query("query") query:String): List<routine>

    @POST("routine/add/{id}")
    suspend fun createRoutine(@Body newRoutine: PostRoutineRequest, @Path("id") id:Int?): Response<Void>

    @GET("routine/detail/{id}")
    suspend fun getRoutineDetail(@Path("id") id:Int?):List<exercise>

    @PUT("routine/removeExercise/{idrut}/{idexc}")
    suspend fun removeExerciseFromRoutine(@Path("idexc") idexc: Int?, @Path("idrut") idrut:Int?): Response<Void>

    @DELETE("routine/delete/{id}")
    suspend fun deleteRoutine(@Path("id") id: Int?):Response<Void>
}