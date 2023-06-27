package com.jder00138218.liftapp.ui.users.admin.exerciseManager.ManageExerciseRequests.viewModel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.jder00138218.liftapp.RetrofitApplication
import com.jder00138218.liftapp.network.ApiResponse
import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.repositories.DetailExerciseRepository
import com.jder00138218.liftapp.ui.login.LoginUiStatus
import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.ui.users.admin.exerciseManager.ManageExerciseRequests.DetailUIStatus
import kotlinx.coroutines.launch

class DetailExerciseViewmodel(private val detailExerciseRepository: DetailExerciseRepository): ViewModel() {
    private var _exercise = mutableStateOf<exercise>(exercise())
    val _status = MutableLiveData<DetailUIStatus>(DetailUIStatus.Resume)

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

     fun denyExercise(id:Int?,navController: NavHostController) {
        viewModelScope.launch {
            _status.value = (
                    when (val response = detailExerciseRepository.denyExercise(id)) {
                        is ApiResponse.Error -> DetailUIStatus.Error(response.exception)
                        is ApiResponse.ErrorWithMessage -> DetailUIStatus.ErrorWithMessage(response.message)
                        is ApiResponse.Success -> DetailUIStatus.Success(
                            response.data
                        )
                    }
                    )
            navController.navigate(Rutas.DashboardAdmin.ruta)

        }
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