package com.jder00138218.liftapp.network.dto.routine

import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.network.dto.user.user

data class routine(
    val id: Int = 0,
    val name: String = "",
    val difficulty: String = "",
    val time: String = "",
    val tag: String = "",
    val user: user = user(),
    val exercises: List<exercise> = emptyList()
)
