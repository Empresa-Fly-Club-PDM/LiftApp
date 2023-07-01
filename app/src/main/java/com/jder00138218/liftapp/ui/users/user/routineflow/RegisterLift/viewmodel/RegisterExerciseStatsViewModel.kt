package com.jder00138218.liftapp.ui.users.user.routineflow.RegisterLift.viewmodel

import android.content.Context
import android.util.Log
import android.view.View
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
import com.jder00138218.liftapp.network.dto.lift.lift
import com.jder00138218.liftapp.repositories.LiftRepository
import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.ui.users.admin.userManager.CreateAdmin.CreateAdminUIStatus
import com.jder00138218.liftapp.ui.users.admin.userManager.CreateAdmin.viewmodel.CreateAdminViewModel
import com.jder00138218.liftapp.ui.users.user.routineflow.RegisterLift.RegisteRexerciseUIStatus
import kotlinx.coroutines.launch

class RegisterExerciseStatsViewModel(private val liftRepository: LiftRepository):ViewModel() {
    private var _weight by mutableStateOf("")
    private var _heihgt by mutableStateOf("")
    val _status = MutableLiveData<RegisteRexerciseUIStatus>(RegisteRexerciseUIStatus.Resume)

    var weight: String
        get() = _weight
        set(value) {
            _weight = value
        }

    var height: String
        get() = _heihgt
        set(value) {
            _heihgt = value
        }

    fun handleUiStatus(navController: NavHostController, context: Context) {
        val status = _status.value
        when (status) {
            is RegisteRexerciseUIStatus.Error -> {
                Log.d("tag", "Error")
                Toast.makeText(context, "Error en el registro", Toast.LENGTH_SHORT).show()
            }
            is RegisteRexerciseUIStatus.ErrorWithMessage -> {
                Toast.makeText(context, "Verificar datos ingresados", Toast.LENGTH_SHORT).show()
            }
            is RegisteRexerciseUIStatus.Success -> {
                Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show()
                navController.popBackStack()
            }
            else -> {
                Log.d("tag","failure")
            }
        }
    }

    private fun create(weight: Double,height:Int,exerciseid:Int?,userid:Int?,navController: NavHostController,context:Context) {
        viewModelScope.launch {
            _status.value = (
                    when (val response = liftRepository.addRecord(weight, height, exerciseid,userid)) {
                        is ApiResponse.Error -> RegisteRexerciseUIStatus.Error(response.exception)
                        is ApiResponse.ErrorWithMessage -> RegisteRexerciseUIStatus.ErrorWithMessage(response.message)
                        is ApiResponse.Success -> RegisteRexerciseUIStatus.Success(
                            response.data
                        )
                    }
                    )
            handleUiStatus(navController,context)
        }
    }

    fun onCreate(exerciseid:Int?,userid:Int?,navController: NavHostController, context: Context) {
        if (!validateData()) {
            _status.value = RegisteRexerciseUIStatus.ErrorWithMessage("Verificar Imformation")
            Toast.makeText(context, "Verificar campos vacios", Toast.LENGTH_SHORT).show()
            return
        }else{
            create(weight.toDouble(),height.toInt(),exerciseid,userid, navController, context)
        }

    }

    private fun validateData(): Boolean {
        when {
            height.isEmpty() -> return false
            weight.isEmpty() -> return false
        }
        return true
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as LiftAppApplication
                RegisterExerciseStatsViewModel(app.liftRepository)
            }
        }
    }
}