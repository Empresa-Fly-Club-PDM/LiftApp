package com.jder00138218.liftapp.ui.users.user.adduserexercises

import java.lang.Exception

sealed class AddUserExercisesUIStatus{
    object Resume : AddUserExercisesUIStatus()
    class Error(val exception: Exception) : AddUserExercisesUIStatus()
    data class ErrorWithMessage(val message: String) : AddUserExercisesUIStatus()
    data class Success(val token: String) : AddUserExercisesUIStatus()
}
