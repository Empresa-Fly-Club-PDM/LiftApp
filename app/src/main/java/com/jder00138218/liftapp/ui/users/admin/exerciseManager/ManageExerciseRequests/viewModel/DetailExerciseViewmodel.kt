package com.jder00138218.liftapp.ui.users.admin.exerciseManager.ManageExerciseRequests.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jder00138218.liftapp.RetrofitApplication
import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.repositories.DetailExerciseRepository
import kotlinx.coroutines.launch

class DetailExerciseViewmodel(private val detailExerciseRepository: DetailExerciseRepository): ViewModel() {
    private var _exercise = mutableStateOf<exercise>(exercise())
    val exercise: exercise
        get() = _exercise.value

    fun addExercise(newExercise: exercise) {
        _exercise.value = newExercise
    }

    fun getDetailExercise(id: Int?) {
        viewModelScope.launch {
            addExercise(detailExerciseRepository.getDetailExercise(id))
        }
        Log.d("checkexercise",_exercise.toString())
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as RetrofitApplication
                DetailExerciseViewmodel(app.detailExerciseRepository)
            }
        }
    }
}