package com.jder00138218.liftapp.ui.users.admin.exerciseManager.CreateExercise.viewmodel

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
import com.jder00138218.liftapp.RetrofitApplication
import com.jder00138218.liftapp.network.ApiResponse
import com.jder00138218.liftapp.repositories.ExerciseRepository
import com.jder00138218.liftapp.ui.login.LoginUiStatus
import com.jder00138218.liftapp.ui.login.decodeHS512TokenWithoutVerification
import com.jder00138218.liftapp.ui.login.getRoleFromTokenPayload
import com.jder00138218.liftapp.ui.login.viewmodel.LoginViewModel
import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.ui.users.admin.exerciseManager.CreateExercise.CreateExerciseUIStatus
import kotlinx.coroutines.launch

class CreateExerciseViewmodel(private val exerciseRepository: ExerciseRepository):ViewModel() {
    private var _description by mutableStateOf("")
    private var _difficulty by mutableStateOf("")
    private var _muscle by mutableStateOf("")
    private var _name by mutableStateOf("")
    private var _reps by mutableStateOf("")
    private var _sets by mutableStateOf("")
    private var _type by mutableStateOf("")
    val _status = MutableLiveData<CreateExerciseUIStatus>(CreateExerciseUIStatus.Resume)

    var description: String
        get() = _description
        set(value) {
            _description = value
        }

    var difficulty: String
        get() = _difficulty
        set(value) {
            _difficulty = value
        }

    var muscle: String
        get() = _muscle
        set(value) {
            _muscle = value
        }

    var name: String
        get() = _name
        set(value) {
            _name = value
        }

    var reps: String
        get() = _reps
        set(value) {
            _reps = value
        }

    var sets: String
        get() = _sets
        set(value) {
            _sets = value
        }

    var type: String
        get() = _type
        set(value) {
            _type = value
        }

    private fun create(description: String,difficulty: String,muscle: String,name: String,reps: Int,sets: Int,type: String,id:Int?,navController: NavHostController,context:Context) {
        viewModelScope.launch {
            _status.value = (
                    when (val response = exerciseRepository.createExercise(description, difficulty,muscle,name,reps,sets,type,id)) {
                        is ApiResponse.Error -> CreateExerciseUIStatus.Error(response.exception)
                        is ApiResponse.ErrorWithMessage -> CreateExerciseUIStatus.ErrorWithMessage(response.message)
                        is ApiResponse.Success -> CreateExerciseUIStatus.Success(
                            response.data
                        )
                    }
                    )
            handleUiStatus(navController,context)
        }
    }


    fun onCreate(navController: NavHostController,context: Context) {
        val app = context.applicationContext as RetrofitApplication
        val userid = app.getUserId()
        if (!validateData()) {
            _status.value = CreateExerciseUIStatus.ErrorWithMessage("Verificar Imformation")
            Toast.makeText(context, "Verificar campos vacios", Toast.LENGTH_SHORT).show()
            return
        }

        create(description, difficulty,muscle,name,reps.toInt(),sets.toInt(),type,userid,navController,context)
    }

    fun handleUiStatus(navController: NavHostController,context:Context) {
        val status = _status.value
        when (status) {
            is CreateExerciseUIStatus.Error -> {
                Log.d("tag", "Error")
                Toast.makeText(context, "Error en inicio de sesión", Toast.LENGTH_SHORT).show()
            }
            is CreateExerciseUIStatus.ErrorWithMessage -> {
                Toast.makeText(context, "Verificar datos ingresados", Toast.LENGTH_SHORT).show()
            }
            is CreateExerciseUIStatus.Success -> {
                Toast.makeText(context, "Ejercicio creado exitosamente", Toast.LENGTH_SHORT).show()
                navController.navigate(route = Rutas.AdminVerifyExercise.ruta)
            }
            else -> {
                Log.d("tag","failure")
            }
        }
    }

    private fun validateData(): Boolean {
        when {
            description.isEmpty() -> return false
            difficulty.isEmpty() -> return false
            muscle.isEmpty() -> return false
            name.isEmpty() -> return false
            reps.isEmpty() -> return false
            sets.isEmpty() ->return false
            type.isEmpty() -> return false
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

    fun clearStatus() {
        _status.value = CreateExerciseUIStatus.Resume
    }



    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as RetrofitApplication
                CreateExerciseViewmodel(app.exerciseRepository)
            }
        }
    }
}