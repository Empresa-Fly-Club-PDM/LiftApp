package com.jder00138218.liftapp.ui.users.user.createroutine

import com.jder00138218.liftapp.ui.users.admin.userManager.CreateAdmin.CreateAdminUIStatus
import java.lang.Exception

sealed class CreateRoutineUIStatus{
    object Resume : CreateRoutineUIStatus()
    class Error(val exception: Exception) : CreateRoutineUIStatus()
    data class ErrorWithMessage(val message: String) : CreateRoutineUIStatus()
    data class Success(val token: String) : CreateRoutineUIStatus()
}
