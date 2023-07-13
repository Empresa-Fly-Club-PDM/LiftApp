package com.jder00138218.liftapp.ui.users.user.addexercisetoroutine

import com.jder00138218.liftapp.ui.users.user.routinedetail.RoutineDetailUIStatus
import java.lang.Exception

sealed class AddExerciseToRoutineUIStatuss{
    object Resume : AddExerciseToRoutineUIStatuss()
    class Error(val exception: Exception) : AddExerciseToRoutineUIStatuss()
    data class ErrorWithMessage(val message: String) : AddExerciseToRoutineUIStatuss()
    data class Success(val token: String) : AddExerciseToRoutineUIStatuss()
}
