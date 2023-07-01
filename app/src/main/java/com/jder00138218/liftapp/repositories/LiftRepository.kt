package com.jder00138218.liftapp.repositories

import android.util.Log
import com.jder00138218.liftapp.network.ApiResponse
import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.network.dto.lift.PostLift
import com.jder00138218.liftapp.network.dto.lift.lift
import com.jder00138218.liftapp.network.dto.user.PostUserRequest
import com.jder00138218.liftapp.network.services.LiftService
import retrofit2.HttpException
import java.io.IOException

class LiftRepository(private val api: LiftService){
    suspend fun getMyHighlight(id:Int?): lift? {
        val Lift: lift? = api.getMyHighligh(id)
        return Lift
    }

    suspend fun addRecord(weight: Int,reps: Int,excid:Int?,userid:Int?): ApiResponse<String> {
        try {
            val response = api.addRecord(PostLift(weight,reps),excid,userid)
            return ApiResponse.Success(response.toString())
        } catch (e: HttpException) {

            if (e.code() == 500) {
                return ApiResponse.ErrorWithMessage("Campos invalidos")
            }
            return ApiResponse.Error(e)
        } catch (e: IOException) {
            return ApiResponse.Error(e)
        }
    }
}