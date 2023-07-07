package com.jder00138218.liftapp.repositories

import android.util.Log
import com.jder00138218.liftapp.network.ApiResponse
import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.network.dto.lift.PostLift
import com.jder00138218.liftapp.network.dto.lift.lift
import com.jder00138218.liftapp.network.dto.user.PostUserRequest
import com.jder00138218.liftapp.network.dto.user.user
import com.jder00138218.liftapp.network.services.LiftService
import retrofit2.HttpException
import java.io.IOException

class LiftRepository(private val api: LiftService){

    suspend fun getLift(id:Int?): lift{
        val Lift:lift = api.getLift(id)
        return Lift
    }

    suspend fun deleteLift(id:Int?): ApiResponse<String> {
        try {
            val response = api.deleteLift(id)
            return ApiResponse.Success(response.toString())
        } catch (e: HttpException) {

            if (e.code() == 410) {
                return ApiResponse.ErrorWithMessage("Eliminado correctamente")
            }
            return ApiResponse.Error(e)
        } catch (e: IOException) {
            return ApiResponse.Error(e)
        }
    }
    suspend fun getMyHighlight(id:Int?): lift? {
        val Lift: lift? = api.getMyHighligh(id)
        return Lift
    }

    suspend fun getMyLifts(id:Int?,query:String):List<lift>{
        val lifts: List<lift> = api.getMyLifts(id,query)
        return lifts
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