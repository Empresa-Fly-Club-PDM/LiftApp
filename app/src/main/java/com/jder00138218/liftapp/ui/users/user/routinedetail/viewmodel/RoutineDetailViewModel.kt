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
import com.jder00138218.liftapp.network.dto.lift.lift
import com.jder00138218.liftapp.network.dto.routine.routine
import com.jder00138218.liftapp.repositories.RoutineRepository
import com.jder00138218.liftapp.ui.users.admin.exerciseManager.VerifiedExerciseView.viewmodel.VerifiedExercisesViewModel
import com.jder00138218.liftapp.ui.users.user.routinedetail.RoutineDetailUIStatus
import com.jder00138218.liftapp.ui.users.user.routineexercisedetail.RoutineExerciseDetailUIStatus
import kotlinx.coroutines.launch

class RoutineDetailViewModel(private val routineRepository: RoutineRepository):ViewModel() {
    private val _exercises = mutableStateListOf<exercise>()
    private var _routine = mutableStateOf<routine>(routine())
    val _status = MutableLiveData<RoutineDetailUIStatus>(RoutineDetailUIStatus.Resume)
    var _time by mutableStateOf(359999)
    val _loading = mutableStateOf(false)


    val exercises: List<exercise>
        get() = _exercises

    fun addRoutine(newRoutine: routine) {
        _routine.value = newRoutine
    }
    fun getRoutineDetails(id:Int?) {
        viewModelScope.launch {
            _exercises.clear()
            _exercises.addAll(routineRepository.getRoutineDetail(id))
        }
    }

    fun getRoutineTime(id: Int?) {
        viewModelScope.launch {
            addRoutine(routineRepository.getRoutineinfo(id))
            _time = convertToSeconds(_routine.value.time)
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

    fun convertToSeconds(timeString: String): Int {
        val parts = timeString.split(":")
        val hours = parts[0].toInt()
        val minutes = parts[1].toInt()
        val seconds = parts[2].toInt()

        val totalSeconds = hours * 3600 + minutes * 60 + seconds
        return totalSeconds
    }
}