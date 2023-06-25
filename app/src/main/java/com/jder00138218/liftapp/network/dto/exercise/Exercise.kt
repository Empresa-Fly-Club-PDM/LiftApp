package com.jder00138218.liftapp.network.dto.exercise

import com.jder00138218.liftapp.network.dto.user.user

data class exercise(
    val id: Int,
    val name: String,
    val muscle: String,
    val difficulty: String,
    val type: String,
    val description: String,
    val sets: Int,
    val reps: Int,
    val verified: Boolean,
    val ownership: Int,
    val user: user
)
