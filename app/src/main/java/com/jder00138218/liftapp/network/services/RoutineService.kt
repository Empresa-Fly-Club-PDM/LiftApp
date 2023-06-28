package com.jder00138218.liftapp.network.services

import com.jder00138218.liftapp.network.dto.lift.lift
import com.jder00138218.liftapp.network.dto.routine.routine
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RoutineService {
    @GET("routine/myRoutines/{id}")
    suspend fun getmyroutines(@Path("id") id:Int?,@Query("query") query:String): List<routine>
}