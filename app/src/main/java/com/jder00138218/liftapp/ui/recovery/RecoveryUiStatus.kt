package com.jder00138218.liftapp.ui.recovery


sealed class RecoveryUiStatus {

    object Resume : RecoveryUiStatus()
    data class Error(val exception: Exception) : RecoveryUiStatus()
    data class ErrorWithMessage(val message: String) : RecoveryUiStatus()
    object Success: RecoveryUiStatus()
}