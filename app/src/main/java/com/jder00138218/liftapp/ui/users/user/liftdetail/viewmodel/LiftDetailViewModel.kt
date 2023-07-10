package com.jder00138218.liftapp.ui.users.user.liftdetail.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.network.dto.lift.lift
import com.jder00138218.liftapp.repositories.LiftRepository
import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.ui.users.admin.exerciseManager.updateexercise.AdminUpdateExerciseUIStatus
import com.jder00138218.liftapp.ui.users.admin.exerciseManager.updateexercise.viewmodel.AdminUpdateExerciseViewModel
import com.jder00138218.liftapp.ui.users.user.liftdetail.LiftDetailUIStatus
import kotlinx.coroutines.launch

class LiftDetailViewModel(private val liftRepository: LiftRepository): ViewModel() {
    private var _lift = mutableStateOf<lift>(lift())
    var _exercisename by mutableStateOf("")
    var _weight by mutableStateOf("")
    var _reps by mutableStateOf("")
    val _status = MutableLiveData<LiftDetailUIStatus>(LiftDetailUIStatus.Resume)

    val lift: lift
        get() = _lift.value


    fun addLift(newLift: lift) {
        _lift.value = newLift
    }

    fun getDetailLift(id: Int?) {
        viewModelScope.launch {
            addLift(liftRepository.getLift(id))
            _exercisename = _lift.value.exercisename
            _weight = _lift.value.weight.toString()
            _reps = _lift.value.reps.toString()
        }
    }

    fun deleteLift(id:Int?, navController: NavHostController, context: Context) {
        viewModelScope.launch {
            _status.value = (
                    when (val response = liftRepository.deleteLift(id)) {
                        is ApiResponse.Error -> LiftDetailUIStatus.Error(response.exception)
                        is ApiResponse.ErrorWithMessage -> LiftDetailUIStatus.ErrorWithMessage(response.message)
                        is ApiResponse.Success -> LiftDetailUIStatus.Success(
                            response.data
                        )
                    }
                    )
            Toast.makeText(context, "Registro Eliminado", Toast.LENGTH_SHORT).show()
            navController.popBackStack()

        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as LiftAppApplication
                LiftDetailViewModel(app.liftRepository)
            }
        }
    }
}