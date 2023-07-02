package com.jder00138218.liftapp.ui.users.user.updateUser.viewmodel

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
import com.jder00138218.liftapp.network.dto.user.user
import com.jder00138218.liftapp.repositories.UserRepository
import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.ui.users.admin.userManager.UpdateAdmin.UpdateAdminUIStatus
import com.jder00138218.liftapp.ui.users.admin.userManager.UpdateAdmin.viewmodel.UpdateAdminViewModel
import com.jder00138218.liftapp.ui.users.user.updateUser.UpdateUserUIStatus
import kotlinx.coroutines.launch
import java.time.LocalDate

class UpdateUserViewModel(private val userRepository: UserRepository): ViewModel() {
    private var _user = mutableStateOf<user>(user())
    var _nombrecompleto by mutableStateOf("")
    var _email by mutableStateOf("")
    var _password by mutableStateOf("")
    var _genero by mutableStateOf("")
    var _fechanac by mutableStateOf("")
    var _weight by mutableStateOf("")
    var _height by mutableStateOf("")
    var _confirmpassowrd by mutableStateOf("")
    var _isVisiblePaswd by mutableStateOf(false)
    val _status = MutableLiveData<UpdateUserUIStatus>(UpdateUserUIStatus.Resume)

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
            _genero = _user.value.genero
            _fechanac =_user.value.fechanac
            _weight = _user.value.weight.toString()
            _height = _user.value.height.toString()
        }
    }
    private fun update(id:Int?, nombrecompleto: String, email: String, password: String,genero:String,fechanac:String,weight:Int,height:Int, navController: NavHostController, context: Context) {
        viewModelScope.launch {
            _status.value = (
                    when (val response = userRepository.editClient(nombrecompleto, email, password,genero,fechanac,weight,height,id)) {
                        is ApiResponse.Error -> UpdateUserUIStatus.Error(response.exception)
                        is ApiResponse.ErrorWithMessage -> UpdateUserUIStatus.ErrorWithMessage(response.message)
                        is ApiResponse.Success -> UpdateUserUIStatus.Success(
                            response.data
                        )
                    }
                    )
            clearData()
            handleUiStatus(navController,context)
        }
    }

    fun onUpdate(id:Int?, navController: NavHostController, context: Context) {
        Log.d("Data",calculateAge(_fechanac).toString())
        if (!validateData()) {
            _status.value = UpdateUserUIStatus.ErrorWithMessage("Verificar Imformation")
            Toast.makeText(context, "Verificar campos vacios", Toast.LENGTH_SHORT).show()
            return
        }
        if(_password.isEmpty() && calculateAge(_fechanac)>=13){
            update(id, _nombrecompleto, _email,_password,_genero,_fechanac,_weight.toInt(),_height.toInt(),navController,context)
        }else if (_password == _confirmpassowrd && _password.length>=8 && calculateAge(_fechanac)>=13){
            update(id, _nombrecompleto, _email,_password,_genero,_fechanac,_weight.toInt(),_height.toInt(),navController,context)
        }else{
            Toast.makeText(context, "Contraseñas muy corta o no coincide", Toast.LENGTH_SHORT).show()
            return
        }
    }

    fun handleUiStatus(navController: NavHostController, context: Context) {
        val status = _status.value
        when (status) {
            is UpdateUserUIStatus.Error -> {
                Log.d("tag", "Error")
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
            is UpdateUserUIStatus.ErrorWithMessage -> {
                Toast.makeText(context, "Verificar datos ingresados", Toast.LENGTH_SHORT).show()
            }
            is UpdateUserUIStatus.Success -> {
                Toast.makeText(context, "Usuario Actualizado", Toast.LENGTH_SHORT).show()
                navController.navigate(route = Rutas.DashboardUser.ruta)
            }
            else -> {
                Log.d("tag","failure")
            }
        }
    }

    fun deleteUser(id:Int?, navController: NavHostController, context: Context) {
        viewModelScope.launch {
            _status.value = (
                    when (val response = userRepository.deleteUser(id)) {
                        is ApiResponse.Error -> UpdateUserUIStatus.Error(response.exception)
                        is ApiResponse.ErrorWithMessage -> UpdateUserUIStatus.ErrorWithMessage(response.message)
                        is ApiResponse.Success -> UpdateUserUIStatus.Success(
                            response.data
                        )
                    }
                    )
            Toast.makeText(context, "Usuario Eliminado", Toast.LENGTH_SHORT).show()
            navController.navigate(Rutas.AdminAdminManager.ruta)

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

    fun calculateAge(dateString: String): Int {
        var userrage = 0;
        val dateParts = dateString.split("/")
        if (dateParts.size == 3) {
            val birthYear = dateParts[2].toInt()
            val currentYear = LocalDate.now().year
            val age = currentYear - birthYear
            userrage = age
        }
        return userrage
    }


    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as LiftAppApplication
                UpdateUserViewModel(app.userRepository)
            }
        }
    }
}