package com.jder00138218.liftapp.repositories

import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.network.dto.routine.routine
import com.jder00138218.liftapp.network.services.RoutineService

class RoutineRepository(private val api: RoutineService) {
    suspend fun getMyRoutines(query:String, id:Int?):List<routine>{
        val routines: List<routine> = api.getmyroutines(id,query)
        return routines
    }
}