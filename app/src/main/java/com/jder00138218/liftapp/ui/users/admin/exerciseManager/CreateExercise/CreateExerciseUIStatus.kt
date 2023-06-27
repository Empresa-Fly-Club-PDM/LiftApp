package com.jder00138218.liftapp.ui.users.admin.exerciseManager.CreateExercise

import com.jder00138218.liftapp.ui.users.admin.exerciseManager.ManageExerciseRequests.DetailUIStatus
import java.lang.Exception

sealed class CreateExerciseUIStatus{
    object Resume : CreateExerciseUIStatus()
    class Error(val exception: Exception) : CreateExerciseUIStatus()
    data class ErrorWithMessage(val message: String) : CreateExerciseUIStatus()
    data class Success(val token: String) : CreateExerciseUIStatus()
}
