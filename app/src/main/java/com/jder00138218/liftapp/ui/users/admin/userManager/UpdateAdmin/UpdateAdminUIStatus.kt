package com.jder00138218.liftapp.ui.users.admin.userManager.UpdateAdmin

import com.jder00138218.liftapp.ui.users.admin.userManager.CreateAdmin.CreateAdminUIStatus
import java.lang.Exception

sealed class UpdateAdminUIStatus {
    object Resume : UpdateAdminUIStatus()
    class Error(val exception: Exception) : UpdateAdminUIStatus()
    data class ErrorWithMessage(val message: String) : UpdateAdminUIStatus()
    data class Success(val token: String) : UpdateAdminUIStatus()
}