package com.jder00138218.liftapp.ui.login.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jder00138218.liftapp.RetrofitApplication
import com.jder00138218.liftapp.network.ApiResponse
import com.jder00138218.liftapp.network.retrofit.RetrofitInstance
import com.jder00138218.liftapp.repositories.CredentialsRepository
import com.jder00138218.liftapp.ui.login.LoginUiStatus
import kotlinx.coroutines.launch

class LoginViewModel(private val credentialsRepository: CredentialsRepository) : ViewModel() {


    private var _email by mutableStateOf("")
    private var _password by mutableStateOf("")
    private var _isVisiblePaswd by mutableStateOf(false)
    private val _status = mutableStateOf<LoginUiStatus>(LoginUiStatus.Resume)

    var email: String = ""
        get() = _email

    var password: String = ""
        get() = _password

    var isVisiblePaswd: Boolean = true
        get() = _isVisiblePaswd

    val status: State<LoginUiStatus>
        get() = _status


    fun changeVisi(value: Boolean): Boolean {
        return value != true
    }

    private fun login(email: String, password: String) {
        viewModelScope.launch {
            _status.value =
                when(val response = credentialsRepository.login(email, password)){
                    is ApiResponse.Error -> LoginUiStatus.Error(response.exception)
                    is ApiResponse.ErrorWithMessage -> LoginUiStatus.ErrorWithMessage(response.message)
                    is ApiResponse.Success -> LoginUiStatus.Success(response.data)
                }

        }
    }

    fun onLogin(){
        if(!validateData()){
            _status.value = LoginUiStatus.ErrorWithMessage("Wrong Imformation")
            return
        }
        login(email, password)
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