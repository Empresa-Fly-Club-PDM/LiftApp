package com.jder00138218.liftapp.network.dto.lift

import com.jder00138218.liftapp.network.dto.user.user

data class lift(
    val id: Int = 0,
    val exercisename: String = "",
    val weight: Int = 0,
    val reps: Int = 0,
    val liftpoints: Int = 0,
    val highlight: Boolean = false,
    val user: user = user()
)
