package com.jder00138218.liftapp.ui.recovery

import com.jder00138218.liftapp.ui.login.LoginUiStatus
import java.lang.Exception

sealed class RecoveryUiStatus {

    object Resume : RecoveryUiStatus()
    class Error(val exception: Exception) : RecoveryUiStatus()
    data class ErrorWithMessage(val message: String) : RecoveryUiStatus()
    data class Success(val token: String) : RecoveryUiStatus()
}