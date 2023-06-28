package com.jder00138218.liftapp.ui.users.admin.userManager.CreateAdmin

import com.jder00138218.liftapp.ui.users.admin.exerciseManager.CreateExercise.CreateExerciseUIStatus
import java.lang.Exception

sealed class CreateAdminUIStatus{
    object Resume : CreateAdminUIStatus()
    class Error(val exception: Exception) : CreateAdminUIStatus()
    data class ErrorWithMessage(val message: String) : CreateAdminUIStatus()
    data class Success(val token: String) : CreateAdminUIStatus()
}
