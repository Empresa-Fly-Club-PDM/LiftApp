package com.jder00138218.liftapp.ui.users.user.routineflow.RegisterLift

import com.jder00138218.liftapp.ui.users.admin.userManager.CreateAdmin.CreateAdminUIStatus
import java.lang.Exception

sealed class RegisteRexerciseUIStatus{
    object Resume : RegisteRexerciseUIStatus()
    class Error(val exception: Exception) : RegisteRexerciseUIStatus()
    data class ErrorWithMessage(val message: String) : RegisteRexerciseUIStatus()
    data class Success(val token: String) : RegisteRexerciseUIStatus()
}
