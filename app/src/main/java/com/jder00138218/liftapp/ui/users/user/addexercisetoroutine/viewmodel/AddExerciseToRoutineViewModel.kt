package com.jder00138218.liftapp.ui.users.user.addexercisetoroutine.viewmodel

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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.jder00138218.liftapp.LiftAppApplication
import com.jder00138218.liftapp.network.ApiResponse
import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.repositories.RoutineRepository
import com.jder00138218.liftapp.ui.users.user.addexercisetoroutine.AddExerciseToRoutineUIStatuss
import com.jder00138218.liftapp.ui.users.user.routinedetail.RoutineDetailUIStatus
import com.jder00138218.liftapp.ui.users.user.routineexercisedetail.RoutineExerciseDetailUIStatus
import com.jder00138218.liftapp.ui.users.user.routineexercisedetail.viewmodel.RoutineExerciseDetailViewModel
import kotlinx.coroutines.launch

class AddExerciseToRoutineViewModel(private val routineRepository: RoutineRepository): ViewModel() {

    val _status = MutableLiveData<AddExerciseToRoutineUIStatuss>(AddExerciseToRoutineUIStatuss.Resume)

    private val _exercises = mutableStateListOf<exercise>()

    val exercises: List<exercise>
        get() = _exercises

    fun searchExerciseDatabase(query:String, id:Int?) {
        viewModelScope.launch {
            _exercises.clear()
            _exercises.addAll(routineRepository.searchExerciseDatabase(query, id))
        }
    }

    fun addExercise(idrout:Int?, idexc:Int?, navController: NavController, context: Context) {
        viewModelScope.launch {
            _status.value = (
                    when (val response = routineRepository.addExercise(idrout,idexc)) {
                        is ApiResponse.Error -> AddExerciseToRoutineUIStatuss.Error(response.exception)
                        is ApiResponse.ErrorWithMessage -> AddExerciseToRoutineUIStatuss.ErrorWithMessage(response.message)
                        is ApiResponse.Success -> AddExerciseToRoutineUIStatuss.Success(
                            response.data
                        )
                    }
                    )
            Toast.makeText(context, "Ejercicio Añadido", Toast.LENGTH_SHORT).show()
        }
    }
    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as LiftAppApplication
                AddExerciseToRoutineViewModel(app.routineRepository)
            }
        }
    }
}