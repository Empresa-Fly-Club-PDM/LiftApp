package com.jder00138218.liftapp.ui.login.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.jder00138218.liftapp.RetrofitApplication
import com.jder00138218.liftapp.network.ApiResponse
import com.jder00138218.liftapp.network.dto.user.user
import com.jder00138218.liftapp.network.retrofit.RetrofitInstance
import com.jder00138218.liftapp.repositories.CredentialsRepository
import com.jder00138218.liftapp.ui.login.LoginUiStatus
import com.jder00138218.liftapp.ui.login.decodeHS512TokenWithoutVerification
import com.jder00138218.liftapp.ui.login.getIdFromTokenPayload
import com.jder00138218.liftapp.ui.login.getRoleFromTokenPayload
import com.jder00138218.liftapp.ui.navigation.Rutas
import kotlinx.coroutines.launch

class LoginViewModel(private val credentialsRepository: CredentialsRepository) : ViewModel() {
    private var _email by mutableStateOf("")
    private var _password by mutableStateOf("")
    private var _isVisiblePaswd by mutableStateOf(false)
    val _status = MutableLiveData<LoginUiStatus>(LoginUiStatus.Resume)


    var email: String get() = _email
        set(value) {
            _email = value
        }

    var password: String get() = _password
        set(value) {
            _password = value
        }

    var isVisiblePaswd: Boolean = true
        get() = _isVisiblePaswd


    private fun login(email: String, password: String,navController: NavHostController,context:Context) {
        viewModelScope.launch {
            _status.value = (
                when (val response = credentialsRepository.login(email, password)) {
                    is ApiResponse.Error -> LoginUiStatus.Error(response.exception)
                    is ApiResponse.ErrorWithMessage -> LoginUiStatus.ErrorWithMessage(response.message)
                    is ApiResponse.Success -> LoginUiStatus.Success(response.data)
                }
            )
            handleUiStatus(navController, context)
        }
    }

    fun onLogin(navController: NavHostController,context:Context) {
        if (!validateData()) {
            _status.value = LoginUiStatus.ErrorWithMessage("Wrong Imformation")
            Toast.makeText(context, "Verificar campos vacios", Toast.LENGTH_SHORT).show()
            return
        }
        login(email, password,navController,context)
    }

    fun handleUiStatus(navController: NavHostController, context: Context) {
        val status = _status.value
        val app = context.applicationContext as RetrofitApplication
        Log.d("tag", "HandleUIState...")
        Log.d("Tag status on function", status.toString())
        when (status) {
            is LoginUiStatus.Error -> {
                Log.d("tag", "Error")
                Toast.makeText(context, "Error en inicio de sesión", Toast.LENGTH_SHORT).show()
            }
            is LoginUiStatus.ErrorWithMessage -> {
                Toast.makeText(context, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
            is LoginUiStatus.Success -> {
                clearStatus()
                clearData()
                app.saveAuthToken(status.token)
                val responInfo = decodeHS512TokenWithoutVerification(status.token)
                val rolUser = getRoleFromTokenPayload(responInfo)
                val userId = getIdFromTokenPayload(responInfo)
                app.saveUserID(userId)
                if (rolUser == "USER") {
                    navController.navigate(route = Rutas.DashboardUser.ruta)
                }
                if (rolUser == "ADMIN") {
                    navController.navigate(route = Rutas.DashboardAdmin.ruta)
                }
            }
            else -> {
                Log.d("tag","failure")
            }
        }
    }

    private fun validateData(): Boolean {
        when {
            email.isEmpty() -> return false
            password.isEmpty() -> return false
        }
        return true
    }

    fun clearData() {
        _email = ""
        _password = ""
    }

    fun clearStatus() {
        _status.value = LoginUiStatus.Resume
    }


    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as RetrofitApplication
                LoginViewModel(app.credentialsRepository)
            }
        }
    }
}