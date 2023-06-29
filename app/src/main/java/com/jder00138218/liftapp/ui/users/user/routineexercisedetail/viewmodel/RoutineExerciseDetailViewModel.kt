package com.jder00138218.liftapp.ui.users.user.routineexercisedetail.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.jder00138218.liftapp.LiftAppApplication
import com.jder00138218.liftapp.network.ApiResponse
import com.jder00138218.liftapp.network.dto.routine.routine
import com.jder00138218.liftapp.repositories.RoutineRepository
import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.ui.users.admin.exerciseManager.ManageExerciseRequests.DetailUIStatus
import com.jder00138218.liftapp.ui.users.admin.exerciseManager.ManageExerciseRequests.viewModel.DetailExerciseViewmodel
import com.jder00138218.liftapp.ui.users.user.routineexercisedetail.RoutineExerciseDetailUIStatus
import kotlinx.coroutines.launch

class RoutineExerciseDetailViewModel(private val routineRepository: RoutineRepository): ViewModel() {
    val _status = MutableLiveData<RoutineExerciseDetailUIStatus>(RoutineExerciseDetailUIStatus.Resume)

    fun removeExercise(idexc:Int?,idrout:Int?, navController: NavHostController, context: Context) {
        viewModelScope.launch {
            _status.value = (
                    when (val response = routineRepository.removExercise(idexc,idrout)) {
                        is ApiResponse.Error -> RoutineExerciseDetailUIStatus.Error(response.exception)
                        is ApiResponse.ErrorWithMessage -> RoutineExerciseDetailUIStatus.ErrorWithMessage(response.message)
                        is ApiResponse.Success -> RoutineExerciseDetailUIStatus.Success(
                            response.data
                        )
                    }
                    )
            Toast.makeText(context, "Ejercicio Removido", Toast.LENGTH_SHORT).show()
            navController.navigate(route = "ruta_user_routine_detail/${idrout}")

        }
    }
    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as LiftAppApplication
                RoutineExerciseDetailViewModel(app.routineRepository)
            }
        }
    }
}