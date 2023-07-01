package com.jder00138218.liftapp.ui.users.user.routineexercisedetail

import com.jder00138218.liftapp.ui.users.admin.exerciseManager.ManageExerciseRequests.DetailUIStatus
import java.lang.Exception

sealed class RoutineExerciseDetailUIStatus{
    object Resume : RoutineExerciseDetailUIStatus()
    class Error(val exception: Exception) : RoutineExerciseDetailUIStatus()
    data class ErrorWithMessage(val message: String) : RoutineExerciseDetailUIStatus()
    data class Success(val token: String) : RoutineExerciseDetailUIStatus()
}
