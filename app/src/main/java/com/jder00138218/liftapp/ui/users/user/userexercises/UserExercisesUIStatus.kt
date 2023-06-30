package com.jder00138218.liftapp.ui.users.user.userexercises

import com.jder00138218.liftapp.ui.users.user.routineexercisedetail.RoutineExerciseDetailUIStatus
import java.lang.Exception

sealed class UserExercisesUIStatus{
    object Resume : UserExercisesUIStatus()
    class Error(val exception: Exception) : UserExercisesUIStatus()
    data class ErrorWithMessage(val message: String) : UserExercisesUIStatus()
    data class Success(val token: String) : UserExercisesUIStatus()
}
