package com.jder00138218.liftapp.ui.users.user.liftdetail

import com.jder00138218.liftapp.ui.users.user.routineexercisedetail.RoutineExerciseDetailUIStatus
import java.lang.Exception

sealed class LiftDetailUIStatus{
    object Resume : LiftDetailUIStatus()
    class Error(val exception: Exception) : LiftDetailUIStatus()
    data class ErrorWithMessage(val message: String) : LiftDetailUIStatus()
    data class Success(val token: String) : LiftDetailUIStatus()
}
