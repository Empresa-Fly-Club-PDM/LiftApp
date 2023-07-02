package com.jder00138218.liftapp.ui.users.user.routinedetail

import com.jder00138218.liftapp.ui.users.user.routineexercisedetail.RoutineExerciseDetailUIStatus
import java.lang.Exception

sealed class RoutineDetailUIStatus{
    object Resume : RoutineDetailUIStatus()
    class Error(val exception: Exception) : RoutineDetailUIStatus()
    data class ErrorWithMessage(val message: String) : RoutineDetailUIStatus()
    data class Success(val token: String) : RoutineDetailUIStatus()
}
