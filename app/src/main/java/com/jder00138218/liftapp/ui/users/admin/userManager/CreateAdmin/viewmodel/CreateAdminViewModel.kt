package com.jder00138218.liftapp.ui.users.admin.userManager.CreateAdmin.viewmodel

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
import com.jder00138218.liftapp.repositories.UserRepository
import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.ui.users.admin.exerciseManager.CreateExercise.CreateExerciseUIStatus
import com.jder00138218.liftapp.ui.users.admin.exerciseManager.CreateExercise.viewmodel.CreateExerciseViewmodel
import com.jder00138218.liftapp.ui.users.admin.userManager.CreateAdmin.CreateAdminUIStatus
import kotlinx.coroutines.launch

class CreateAdminViewModel(private val userRepository: UserRepository): ViewModel() {
    private var _nombrecompleto by mutableStateOf("")
    private var _email by mutableStateOf("")
    private var _password by mutableStateOf("")
    private var _confirmpassowrd by mutableStateOf("")
    private var _isVisiblePaswd by mutableStateOf(false)
    val _status = MutableLiveData<CreateAdminUIStatus>(CreateAdminUIStatus.Resume)


    var nombrecompleto: String
        get() = _nombrecompleto
        set(value) {
            _nombrecompleto = value
        }

    var email: String
        get() = _email
        set(value) {
            _email = value
        }
    var password:String
        get()= _password
        set(value){
            _password=value
        }
    var confirmpassword:String
        get()= _confirmpassowrd
        set(value){
            _confirmpassowrd=value
        }

    var isVisiblePaswd: Boolean = true
        get() = _isVisiblePaswd


    private fun create(nombrecompleto: String,email: String,password: String,navController: NavHostController,context:Context) {
        viewModelScope.launch {
            _status.value = (
                    when (val response = userRepository.createUser(nombrecompleto, email, password)) {
                        is ApiResponse.Error -> CreateAdminUIStatus.Error(response.exception)
                        is ApiResponse.ErrorWithMessage -> CreateAdminUIStatus.ErrorWithMessage(response.message)
                        is ApiResponse.Success -> CreateAdminUIStatus.Success(
                            response.data
                        )
                    }
                    )
            handleUiStatus(navController,context)
        }
    }

    fun handleUiStatus(navController: NavHostController,context:Context) {
        val status = _status.value
        when (status) {
            is CreateAdminUIStatus.Error -> {
                Log.d("tag", "Error")
                Toast.makeText(context, "Error en el registro", Toast.LENGTH_SHORT).show()
            }
            is CreateAdminUIStatus.ErrorWithMessage -> {
                Toast.makeText(context, "Verificar datos ingresados", Toast.LENGTH_SHORT).show()
            }
            is CreateAdminUIStatus.Success -> {
                Toast.makeText(context, "Administrador Creado exitosamente", Toast.LENGTH_SHORT).show()
                navController.navigate(route = Rutas.AdminAdminManager.ruta)
            }
            else -> {
                Log.d("tag","failure")
            }
        }
    }

    fun onCreate(navController: NavHostController, context: Context) {
        if (!validateData()) {
            _status.value = CreateAdminUIStatus.ErrorWithMessage("Verificar Imformation")
            Toast.makeText(context, "Verificar campos vacios", Toast.LENGTH_SHORT).show()
            return
        }
        if(password==confirmpassword){
            create(nombrecompleto, email,password,navController,context)
        }else{
            Toast.makeText(context, "Contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return
        }

    }

    private fun validateData(): Boolean {
        when {
            nombrecompleto.isEmpty() -> return false
            email.isEmpty() -> return false
            password.isEmpty() -> return false
            confirmpassword.isEmpty() -> return false
        }
        return true
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as RetrofitApplication
                CreateAdminViewModel(app.userRepository)
            }
        }
    }
}