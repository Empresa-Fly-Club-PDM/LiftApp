package com.jder00138218.liftapp.repositories

import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.network.services.ExerciseService

class ExerciseRepository(private val api:ExerciseService) {
    suspend fun getSolicitudes(query:String):List<exercise>{
        val exercises: List<exercise> = api.getSolicitudes(query)
        return exercises
    }
}