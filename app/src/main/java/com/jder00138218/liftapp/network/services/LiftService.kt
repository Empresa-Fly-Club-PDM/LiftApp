package com.jder00138218.liftapp.network.services

import com.jder00138218.liftapp.network.dto.lift.PostLift
import com.jder00138218.liftapp.network.dto.lift.lift
import com.jder00138218.liftapp.network.dto.routine.PostRoutineRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface LiftService {
    @GET("lift/myHighlight/{id}")
    suspend fun getMyHighligh(@Path("id") id: Int?): lift?

    @GET("lift/mylifts/{id}")
    suspend fun getMyLifts(@Path("id") id: Int?, @Query("query") query: String): List<lift>

    @POST("lift/add/{excid}/{userid}")
    suspend fun addRecord(
        @Body newRecord: PostLift,
        @Path("excid") excid: Int?,
        @Path("userid") userid: Int?
    ): Response<Void>

    @GET("lift/detail/{id}")
    suspend fun getLift(@Path("id") id: Int?): lift

    @DELETE("lift/delete/{id}")
    suspend fun deleteLift(@Path("id") id: Int?): Response<Void>
}