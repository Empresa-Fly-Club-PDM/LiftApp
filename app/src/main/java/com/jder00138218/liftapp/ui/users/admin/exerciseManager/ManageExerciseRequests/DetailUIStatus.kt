package com.jder00138218.liftapp.ui.users.admin.exerciseManager.ManageExerciseRequests

import java.lang.Exception

    sealed class DetailUIStatus {
        object Resume : DetailUIStatus()
        class Error(val exception: Exception) : DetailUIStatus()
        data class ErrorWithMessage(val message: String) : DetailUIStatus()
        data class Success(val token: String) : DetailUIStatus()
    }
