package com.jder00138218.liftapp.repositories

import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.network.dto.lift.lift
import com.jder00138218.liftapp.network.services.LiftService

class LiftRepository(private val api: LiftService){
    suspend fun getMyHighlight(id:Int?): lift? {
        val Lift: lift? = api.getMyHighligh(id)
        return Lift
    }
}