package com.jder00138218.liftapp.ui.users.admin.exerciseManager.updateexercise.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.jder00138218.liftapp.LiftAppApplication
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.network.ApiResponse
import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.repositories.ExerciseRepository
import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.ui.users.admin.exerciseManager.updateexercise.AdminUpdateExerciseUIStatus
import kotlinx.coroutines.launch

class AdminUpdateExerciseViewModel(private val exerciseRepository: ExerciseRepository):ViewModel() {
    private var _exercise = mutableStateOf<exercise>(exercise())
    var _description by mutableStateOf("")
    var _difficulty by mutableStateOf("")
    var _muscle by mutableStateOf("")
    var _name by mutableStateOf("")
    var _reps by mutableStateOf("")
    var _sets by mutableStateOf("")
    var _type by mutableStateOf("")
    val _status = MutableLiveData<AdminUpdateExerciseUIStatus>(AdminUpdateExerciseUIStatus.Resume)
    val isLoadingUpdate = mutableStateOf(false)
    val isLoadingDelete = mutableStateOf(false)




    val exercise: exercise
        get() = _exercise.value

    fun addExercise(newExercise: exercise) {
        _exercise.value = newExercise
    }

    fun getDetailExercise(id: Int?) {
        viewModelScope.launch {
            addExercise(exerciseRepository.getDetailExercise(id))
            _description= _exercise.value.description
            _difficulty= _exercise.value.difficulty
            _muscle=_exercise.value.muscle
            _name = _exercise.value.name
            _reps = _exercise.value.reps.toString()
            _sets = _exercise.value.sets.toString()
            _type = _exercise.value.type
        }
    }

    private fun update(description: String,difficulty: String,muscle: String,name: String,reps: Int,sets: Int,type: String,id:Int?,navController: NavHostController,context:Context) {
        viewModelScope.launch {
            _status.value = (
                    when (val response = exerciseRepository.editExercise(description, difficulty,muscle,name,reps,sets,type,id)) {
                        is ApiResponse.Error -> AdminUpdateExerciseUIStatus.Error(response.exception)
                        is ApiResponse.ErrorWithMessage -> AdminUpdateExerciseUIStatus.ErrorWithMessage(response.message)
                        is ApiResponse.Success -> AdminUpdateExerciseUIStatus.Success(
                            response.data
                        )
                    }
                    )
            clearData()
            handleUiStatus(navController,context)
        }
    }


    fun onUpdate(navController: NavHostController, context: Context,exerciseid:Int?) {
        if (!validateData()) {
            _status.value = AdminUpdateExerciseUIStatus.ErrorWithMessage(context.getString(R.string.wrong_imformation))
            Toast.makeText(context, context.getString(R.string.verificar_campos_vacios), Toast.LENGTH_SHORT).show()
            isLoadingUpdate.value = false
            return
        }

        update(_description, _difficulty,_muscle,_name,_reps.toInt(),_sets.toInt(),_type,exerciseid,navController,context)
    }

    fun handleUiStatus(navController: NavHostController,context:Context) {
        val status = _status.value
        when (status) {
            is AdminUpdateExerciseUIStatus.Error -> {
                Toast.makeText(context, context.getString(R.string.error), Toast.LENGTH_SHORT).show()
            }
            is AdminUpdateExerciseUIStatus.ErrorWithMessage -> {
                Toast.makeText(context, context.getString(R.string.verificar_datos_ingresados), Toast.LENGTH_SHORT).show()
            }
            is AdminUpdateExerciseUIStatus.Success -> {
                Toast.makeText(context, context.getString(R.string.ejercicio_actualizado_exitosamente), Toast.LENGTH_SHORT).show()
                navController.navigate(route = Rutas.AdminVerifyExercise.ruta)
            }
            else -> {
                Log.d("tag","")
            }
        }
        isLoadingUpdate.value = true
    }

    fun deleteExercise(id:Int?,navController: NavHostController,context:Context) {
        viewModelScope.launch {
            _status.value = (
                    when (val response = exerciseRepository.deleteExercise(id)) {
                        is ApiResponse.Error -> AdminUpdateExerciseUIStatus.Error(response.exception)
                        is ApiResponse.ErrorWithMessage -> AdminUpdateExerciseUIStatus.ErrorWithMessage(response.message)
                        is ApiResponse.Success -> AdminUpdateExerciseUIStatus.Success(
                            response.data
                        )
                    }
                    )
            Toast.makeText(context, context.getString(R.string.ejercicio_eliminado), Toast.LENGTH_SHORT).show()
            isLoadingDelete.value = true
            navController.navigate(Rutas.AdminVerifyExercise.ruta)

        }
    }
    private fun validateData(): Boolean {
        when {
            _description.isEmpty() -> return false
            _difficulty.isEmpty() -> return false
            _muscle.isEmpty() -> return false
            _name.isEmpty() -> return false
            _reps.isEmpty() -> return false
            _sets.isEmpty() ->return false
            _type.isEmpty() -> return false
        }
        return true
    }

    fun clearData() {
        _description = ""
        _difficulty = ""
        _muscle=""
        _name=""
        _reps=""
        _sets=""
        _type=""
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as LiftAppApplication
                AdminUpdateExerciseViewModel(app.exerciseRepository)
            }
        }
    }
}