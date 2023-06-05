package com.jder00138218.liftapp.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jder00138218.liftapp.repositories.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel() : ViewModel() {
    private val userRepo = UserRepository()

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var isVisiblePaswd by mutableStateOf(false)


    fun changeVisi(value: Boolean): Boolean {
        return value != true
    }

    fun onLoginClicked() {

        viewModelScope.launch {
            val users = userRepo.getAllUsers()
            val isEmailValid = users.any { it.email == email }
            val isPassValid = users.any { it.password == password }

            if (isEmailValid && isPassValid) {
                // El email es válido
                // TODO() -> next view
            } else {
                // El email no es válido
                // TODO() -> show error to users
            }
        }
    }


}