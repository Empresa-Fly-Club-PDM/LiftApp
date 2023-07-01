package com.jder00138218.liftapp.ui.users.user.createroutine.viewmodel

import android.content.Context
import android.util.Log
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
import com.jder00138218.liftapp.network.dto.routine.routine
import com.jder00138218.liftapp.repositories.RoutineRepository
import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.ui.users.admin.userManager.CreateAdmin.CreateAdminUIStatus
import com.jder00138218.liftapp.ui.users.admin.userManager.CreateAdmin.viewmodel.CreateAdminViewModel
import com.jder00138218.liftapp.ui.users.user.createroutine.CreateRoutineUIStatus
import kotlinx.coroutines.launch

class CreateRoutineViewModel(private val routineRepository: RoutineRepository) : ViewModel(){

    private var _difficulty by mutableStateOf("")
    private var _name by mutableStateOf("")
    private var _tag by mutableStateOf("")
    private var _minute by mutableStateOf("")
    private var _hour by mutableStateOf("00")
    private var _time by mutableStateOf("")
    val _status = MutableLiveData<CreateRoutineUIStatus>(CreateRoutineUIStatus.Resume)

    var difficulty: String
        get() = _difficulty
        set(value) {
            _difficulty = value
        }
    var name: String
        get() = _name
        set(value) {
            _name = value
        }
    var tag: String
        get() = _tag
        set(value) {
            _tag = value
        }
    var minute: String
        get() = _minute
        set(value) {
            _minute = value
        }
    var hour: String
        get() = _hour
        set(value) {
            _hour = value
        }

    var time: String
        get() = _time
        set(value) {
            _time = value
        }

    private fun create(difficulty: String,name: String,tag: String,Minute:String,Hour:String,userid:Int?,navController: NavHostController,context:Context) {
        _time = "${Hour}:${Minute}:00"
        val time = time
        viewModelScope.launch {
            _status.value = (
                    when (val response = routineRepository.createRoutine(difficulty,name,tag,time,userid)) {
                        is ApiResponse.Error -> CreateRoutineUIStatus.Error(response.exception)
                        is ApiResponse.ErrorWithMessage -> CreateRoutineUIStatus.ErrorWithMessage(response.message)
                        is ApiResponse.Success -> CreateRoutineUIStatus.Success(
                            response.data
                        )
                    }
                    )
            handleUiStatus(navController,context)
        }
    }

    fun onCreate(navController: NavHostController, context: Context,id:Int?) {
        if (!validateData()) {
            _status.value = CreateRoutineUIStatus.ErrorWithMessage("Verificar Imformation")
            Toast.makeText(context, "Verificar campos vacios", Toast.LENGTH_SHORT).show()
            return
        }
        if(minute.toInt()<=60 && minute.toInt()>0 && hour.toInt()>=0){
            create(difficulty, name,tag,minute,hour,id,navController,context)
        }else{
            Toast.makeText(context, "Duracipon de entrenamiento invalida", Toast.LENGTH_SHORT).show()
            return
        }

    }

    fun handleUiStatus(navController: NavHostController, context: Context) {
        val status = _status.value
        when (status) {
            is CreateRoutineUIStatus.Error -> {
                Log.d("tag", "Error")
                Toast.makeText(context, "Error en el registro", Toast.LENGTH_SHORT).show()
            }
            is CreateRoutineUIStatus.ErrorWithMessage -> {
                Toast.makeText(context, "Verificar datos ingresados", Toast.LENGTH_SHORT).show()
            }
            is CreateRoutineUIStatus.Success -> {
                Toast.makeText(context, "Rutina Creada exitosamente", Toast.LENGTH_SHORT).show()
                navController.navigate(route = Rutas.UserRoutineMenu.ruta)
            }
            else -> {
                Log.d("tag","failure")
            }
        }
    }

    private fun validateData(): Boolean {
        when {
            difficulty.isEmpty() -> return false
            name.isEmpty() -> return false
            tag.isEmpty() -> return false
            minute.isEmpty() -> return false
            hour.isEmpty() -> return false
        }
        return true
    }


    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as LiftAppApplication
                CreateRoutineViewModel(app.routineRepository)
            }
        }
    }
}