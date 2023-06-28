package com.jder00138218.liftapp.ui.users.admin.exerciseManager.VerifiedExerciseView.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jder00138218.liftapp.LiftAppApplication
import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.repositories.ExerciseRepository
import kotlinx.coroutines.launch

class VerifiedExercisesViewModel(private val exerciseRepository: ExerciseRepository):ViewModel() {
    private val _exercises = mutableStateListOf<exercise>()
    val exercises: List<exercise>
        get() = _exercises

    fun getVeriedEercises(query:String) {
        viewModelScope.launch {
            _exercises.clear()
            _exercises.addAll(exerciseRepository.getVerified(query))
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as LiftAppApplication
                VerifiedExercisesViewModel(app.exerciseRepository)
            }
        }
    }
}