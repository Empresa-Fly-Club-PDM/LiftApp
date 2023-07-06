package com.jder00138218.liftapp.ui.users.user.routinedetail.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.jder00138218.liftapp.LiftAppApplication
import com.jder00138218.liftapp.network.ApiResponse
import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.repositories.RoutineRepository
import com.jder00138218.liftapp.ui.users.admin.exerciseManager.VerifiedExerciseView.viewmodel.VerifiedExercisesViewModel
import com.jder00138218.liftapp.ui.users.user.routinedetail.RoutineDetailUIStatus
import com.jder00138218.liftapp.ui.users.user.routineexercisedetail.RoutineExerciseDetailUIStatus
import kotlinx.coroutines.launch

class RoutineDetailViewModel(private val routineRepository: RoutineRepository):ViewModel() {
    private val _exercises = mutableStateListOf<exercise>()
    val _status = MutableLiveData<RoutineDetailUIStatus>(RoutineDetailUIStatus.Resume)
    var _time by mutableStateOf(0)

    val exercises: List<exercise>
        get() = _exercises
    fun getRoutineDetails(id:Int?) {
        viewModelScope.launch {
            _exercises.clear()
            _exercises.addAll(routineRepository.getRoutineDetail(id))
        }
    }

    fun removeRouitne(id:Int?, navController: NavController, context: Context) {
        viewModelScope.launch {
            _status.value = (
                    when (val response = routineRepository.deleteRoutine(id)) {
                        is ApiResponse.Error -> RoutineDetailUIStatus.Error(response.exception)
                        is ApiResponse.ErrorWithMessage -> RoutineDetailUIStatus.ErrorWithMessage(response.message)
                        is ApiResponse.Success -> RoutineDetailUIStatus.Success(
                            response.data
                        )
                    }
                    )
            Toast.makeText(context, "Rutina Eliminada", Toast.LENGTH_SHORT).show()
            navController.popBackStack()

        }
    }
    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as LiftAppApplication
                RoutineDetailViewModel(app.routineRepository)
            }
        }
    }

    fun convertToMilliseconds(timeString: String): Long {
        val parts = timeString.split(":")
        val hours = parts[0].toLong()
        val minutes = parts[1].toLong()
        val seconds = parts[2].toLong()

        val totalMilliseconds = hours * 60 * 60 * 1000 + minutes * 60 * 1000 + seconds * 1000
        return totalMilliseconds
    }

}