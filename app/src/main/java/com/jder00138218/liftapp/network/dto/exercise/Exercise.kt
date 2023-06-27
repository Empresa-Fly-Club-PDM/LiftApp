package com.jder00138218.liftapp.network.dto.exercise

import com.jder00138218.liftapp.network.dto.user.user

data class exercise(
    val id: Int = 0,
    val name: String = "",
    val muscle: String = "",
    val difficulty: String = "",
    val type: String = "",
    val description: String = "",
    val sets: Int = 0,
    val reps: Int = 0,
    val verified: Boolean = false,
    val ownership: Int = 0,
    val user: user = user()
)


