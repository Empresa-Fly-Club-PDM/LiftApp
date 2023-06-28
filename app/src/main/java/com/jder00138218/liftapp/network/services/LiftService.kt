package com.jder00138218.liftapp.network.services

import com.jder00138218.liftapp.network.dto.lift.lift
import retrofit2.http.GET
import retrofit2.http.Path

interface LiftService {
    @GET("lift/myHighlight/{id}")
    suspend fun getMyHighligh(@Path("id") id:Int?):lift?
}