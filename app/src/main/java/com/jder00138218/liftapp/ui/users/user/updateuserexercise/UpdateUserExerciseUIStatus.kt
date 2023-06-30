package com.jder00138218.liftapp.ui.users.user.updateuserexercise

import com.jder00138218.liftapp.ui.users.user.adduserexercises.AddUserExercisesUIStatus
import java.lang.Exception

sealed class UpdateUserExerciseUIStatus{
    object Resume : UpdateUserExerciseUIStatus()
    class Error(val exception: Exception) : UpdateUserExerciseUIStatus()
    data class ErrorWithMessage(val message: String) : UpdateUserExerciseUIStatus()
    data class Success(val token: String) : UpdateUserExerciseUIStatus()
}
