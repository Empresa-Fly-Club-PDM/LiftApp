package com.jder00138218.liftapp.ui.register.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.jder00138218.liftapp.RetrofitApplication
import com.jder00138218.liftapp.network.ApiResponse
import com.jder00138218.liftapp.repositories.CredentialsRepository
import com.jder00138218.liftapp.ui.login.LoginUiStatus
import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.ui.register.RegisterUiStatus
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: CredentialsRepository) : ViewModel() {
    private var _name by mutableStateOf("")
    private var _email by mutableStateOf("")
    private var _password by mutableStateOf("")
    private var _passwordVe by mutableStateOf("")
    private var _genre by mutableStateOf("")
    private var _date by mutableStateOf("")
    private var _weigth by mutableStateOf(0)
    private var _height by mutableStateOf(0.0)
    private var _isVisiblePaswd by mutableStateOf(false)
    private var _status = mutableStateOf<RegisterUiStatus>(RegisterUiStatus.Resume)

    val status: State<RegisterUiStatus>
        get() = _status

    var name: String
        get() = _name
        set(value) {
            _name = value
        }

    var email: String
        get() = _email
        set(value) {
            _email = value
        }

    var password: String
        get() = _password
        set(value) {
            _password = value
        }

    var passwordVe: String
        get() = _passwordVe
        set(value) {
            _passwordVe = value
        }


    var genre: String
        get() = _genre
        set(value) {
            _genre = value
        }
    var date: String
        get() = _date
        set(value) {
            _date = value
        }

    var weigth: Int
        get() = _weigth
        set(value) {
            _weigth = value
        }

    var heigth: Double
        get() = _height
        set(value) {
            _height = value
        }

    var isVisiblePaswd: Boolean = true
        get() = _isVisiblePaswd

    private fun register(
        name: String,
        email: String,
        password: String,
        genre: String,
        date: String,
        weigth: Int,
        heigth: Double,
        navController: NavHostController
    ) {
        viewModelScope.launch {
            _status.value = (
                    when (val response =
                        repository.register(name, email, password, genre, date, weigth, heigth)) {
                        is ApiResponse.Error -> RegisterUiStatus.Error(response.exception)
                        is ApiResponse.ErrorWithMessage -> RegisterUiStatus.ErrorWithMessage(
                            response.message
                        )

                        is ApiResponse.Success -> RegisterUiStatus.Success
                    }
                    )
            handleUiStatus(navController)
        }
    }

    fun onRegister(navController: NavHostController) {
        if (!validateData()) {
            _status.value = RegisterUiStatus.ErrorWithMessage("Wrong Information")
            return
        }
        Log.d("data", name)
        Log.d("data", email)
        Log.d("data", password)
        Log.d("data", genre)
        Log.d("data", date)
        Log.d("data", weigth.toString())
        Log.d("data", heigth.toString())
        register(name, email, password, genre, date, weigth, heigth, navController)
    }

    private fun validateData(): Boolean {
        when {
            email.isEmpty() -> return false
            password.isEmpty() -> return false
            genre.isEmpty() -> return false
            date.isEmpty() -> return false
            (weigth == 0) -> return false
            (heigth == 0.0) -> return false
        }
        return true
    }

    fun clearStatus() {
        _status.value = RegisterUiStatus.Resume
    }


    fun clearData() {
        _name = ""
        _email = ""
        _password = ""
        _genre = ""
        _date = ""
        _height = 0.0
        _weigth = 0
    }

    fun handleUiStatus(
        navController: NavHostController,
    ) {
        val status = _status.value

        when (status) {

            is RegisterUiStatus.Error -> {
                Log.d("tag", "Error")
                // TODO() -> Toast.makeText(requireContext(), "An error has occurred", Toast.LENGTH_SHORT).show()
            }

            is RegisterUiStatus.ErrorWithMessage -> {
                //  TODO() -> Toast.makeText(requireContext(), status.message, Toast.LENGTH_SHORT).show()
                Log.d("tag", "Error with message")
            }

            is RegisterUiStatus.Success -> {
                clearStatus()
                clearData()
                navController.navigate(route = Rutas.Login.ruta)
            }

            else -> {}
        }
    }


    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as RetrofitApplication
                RegisterViewModel(app.credentialsRepository)
            }
        }
    }

}


