package com.jder00138218.liftapp.ui.users.admin.userManager.UpdateAdmin.viewmodel

import android.content.Context
import android.provider.SyncStateContract.Helpers.update
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
import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.network.dto.user.user
import com.jder00138218.liftapp.repositories.UserRepository
import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.ui.users.admin.exerciseManager.updateexercise.AdminUpdateExerciseUIStatus
import com.jder00138218.liftapp.ui.users.admin.userManager.CreateAdmin.CreateAdminUIStatus
import com.jder00138218.liftapp.ui.users.admin.userManager.CreateAdmin.viewmodel.CreateAdminViewModel
import com.jder00138218.liftapp.ui.users.admin.userManager.UpdateAdmin.UpdateAdminUIStatus
import kotlinx.coroutines.launch

class UpdateAdminViewModel(private val userRepository: UserRepository):ViewModel() {
    private var _user = mutableStateOf<user>(user())
    var _nombrecompleto by mutableStateOf("")
    var _email by mutableStateOf("")
    var _password by mutableStateOf("")
    var _confirmpassowrd by mutableStateOf("")
    var _isVisiblePaswd by mutableStateOf(false)
    val _status = MutableLiveData<UpdateAdminUIStatus>(UpdateAdminUIStatus.Resume)
    val _loading = mutableStateOf(false)

    val user: user
        get() = _user.value

    fun addUser(newUser: user) {
        _user.value = newUser
    }

    fun getUserDetail(id: Int?) {
        viewModelScope.launch {
            addUser(userRepository.getUserDetails(id))
            _nombrecompleto= _user.value.nombrecompleto
            _email= _user.value.email
        }
        Log.d("checkexercise",_user.toString())
    }
    private fun update(id:Int?, nombrecompleto: String,email: String,password: String,navController: NavHostController,context:Context) {
        viewModelScope.launch {
            _status.value = (
                    when (val response = userRepository.editUser(nombrecompleto, email, password,id)) {
                        is ApiResponse.Error -> UpdateAdminUIStatus.Error(response.exception)
                        is ApiResponse.ErrorWithMessage -> UpdateAdminUIStatus.ErrorWithMessage(response.message)
                        is ApiResponse.Success -> UpdateAdminUIStatus.Success(
                            response.data
                        )
                    }
                    )
            clearData()
            handleUiStatus(navController,context)
        }
    }

    fun onUpdate(id:Int?, navController: NavHostController, context: Context) {
        if (!validateData()) {
            _status.value = UpdateAdminUIStatus.ErrorWithMessage("Verificar Imformation")
            Toast.makeText(context, "Verificar campos vacios", Toast.LENGTH_SHORT).show()
            _loading.value=false
            return
        }
        if(_password.isEmpty()){
            update(id, _nombrecompleto, _email,_password,navController,context)
        }else if (_password == _confirmpassowrd && _password.length>=8){
            update(id, _nombrecompleto, _email,_password,navController,context)
        }else{
            Toast.makeText(context, "Contraseñas muy corta o no coincide", Toast.LENGTH_SHORT).show()
            _loading.value=false
            return
        }
    }

    fun handleUiStatus(navController: NavHostController, context: Context) {
        val status = _status.value
        when (status) {
            is UpdateAdminUIStatus.Error -> {
                Log.d("tag", "Error")
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
            is UpdateAdminUIStatus.ErrorWithMessage -> {
                Toast.makeText(context, "Verificar datos ingresados", Toast.LENGTH_SHORT).show()
            }
            is UpdateAdminUIStatus.Success -> {
                Toast.makeText(context, "Usuario Actualizado", Toast.LENGTH_SHORT).show()
                navController.navigate(route = Rutas.AdminAdminManager.ruta)
            }
            else -> {
                Log.d("tag","failure")
            }
        }

        _loading.value=false
    }

    fun deleteUser(id:Int?,navController: NavHostController,context:Context) {
        viewModelScope.launch {
            _status.value = (
                    when (val response = userRepository.deleteUser(id)) {
                        is ApiResponse.Error -> UpdateAdminUIStatus.Error(response.exception)
                        is ApiResponse.ErrorWithMessage -> UpdateAdminUIStatus.ErrorWithMessage(response.message)
                        is ApiResponse.Success -> UpdateAdminUIStatus.Success(
                            response.data
                        )
                    }
                    )
            Toast.makeText(context, "Usuario Eliminado", Toast.LENGTH_SHORT).show()
            navController.navigate(Rutas.AdminAdminManager.ruta)
            _loading.value=false

        }
    }

    private fun validateData(): Boolean {
        when {
            _nombrecompleto.isEmpty() -> return false
            _email.isEmpty() -> return false
        }
        return true
    }


    fun clearData() {
        _nombrecompleto = ""
        _email = ""
    }


    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as LiftAppApplication
                UpdateAdminViewModel(app.userRepository)
            }
        }
    }
}