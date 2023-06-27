package com.jder00138218.liftapp.network.dto.exercise

data class PostVerifiedExerciseRequest(
    val description: String,
    val difficulty: String,
    val muscle: String,
    val name: String,
    val reps: Int,
    val sets: Int,
    val type: String
)
