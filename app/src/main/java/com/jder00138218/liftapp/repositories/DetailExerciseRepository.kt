package com.jder00138218.liftapp.repositories

import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.network.services.VerifyDenyExerciseService

class DetailExerciseRepository(private val api: VerifyDenyExerciseService) {
    suspend fun getDetailExercise(id:Int?): exercise {
        val exercise: exercise = api.getExcDetail(id)
        return exercise
    }
}