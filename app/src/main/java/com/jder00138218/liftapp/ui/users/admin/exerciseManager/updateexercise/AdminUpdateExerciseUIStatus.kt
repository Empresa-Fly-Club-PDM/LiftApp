package com.jder00138218.liftapp.ui.users.admin.exerciseManager.updateexercise

import com.jder00138218.liftapp.ui.users.admin.exerciseManager.ManageExerciseRequests.DetailUIStatus
import java.lang.Exception

sealed class AdminUpdateExerciseUIStatus{
    object Resume : AdminUpdateExerciseUIStatus()
    class Error(val exception: Exception) : AdminUpdateExerciseUIStatus()
    data class ErrorWithMessage(val message: String) : AdminUpdateExerciseUIStatus()
    data class Success(val token: String) : AdminUpdateExerciseUIStatus()
}
