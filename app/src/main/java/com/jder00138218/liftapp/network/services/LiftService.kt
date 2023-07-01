package com.jder00138218.liftapp.network.services

import com.jder00138218.liftapp.network.dto.lift.PostLift
import com.jder00138218.liftapp.network.dto.lift.lift
import com.jder00138218.liftapp.network.dto.routine.PostRoutineRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LiftService {
    @GET("lift/myHighlight/{id}")
    suspend fun getMyHighligh(@Path("id") id:Int?):lift?

    @POST("lift/add/{excid}({userid}")
    suspend fun addRecord(@Body newRecord: PostLift, @Path("excid") excid:Int?, @Path("userid")userid:Int?): Response<Void>
}