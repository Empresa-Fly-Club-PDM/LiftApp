package com.jder00138218.liftapp.ui.users.user.updateUser

import com.jder00138218.liftapp.ui.users.admin.userManager.UpdateAdmin.UpdateAdminUIStatus
import java.lang.Exception

sealed class UpdateUserUIStatus {
    object Resume : UpdateUserUIStatus()
    class Error(val exception: Exception) : UpdateUserUIStatus()
    data class ErrorWithMessage(val message: String) : UpdateUserUIStatus()
    data class Success(val token: String) : UpdateUserUIStatus()
}