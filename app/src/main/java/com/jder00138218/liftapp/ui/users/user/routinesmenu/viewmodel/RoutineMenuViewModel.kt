package com.jder00138218.liftapp.ui.users.user.routinesmenu.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jder00138218.liftapp.LiftAppApplication
import com.jder00138218.liftapp.network.dto.routine.routine
import com.jder00138218.liftapp.network.dto.user.user
import com.jder00138218.liftapp.repositories.RoutineRepository
import com.jder00138218.liftapp.ui.users.admin.exerciseManager.ManageExerciseRequests.viewModel.DetailExerciseViewmodel
import kotlinx.coroutines.launch

class RoutineMenuViewModel(private val routineRepository: RoutineRepository):ViewModel() {
    private val _routines = mutableStateListOf<routine>()

    val routines: List<routine>
        get() = _routines

    fun getMyRoutines(query:String,id:Int?) {
        viewModelScope.launch {
            _routines.clear()
            _routines.addAll(routineRepository.getMyRoutines(query,id))
        }
    }


    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as LiftAppApplication
                RoutineMenuViewModel(app.routineRepository)
            }
        }
    }
}