package com.jder00138218.liftapp.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel() : ViewModel() {

    var email by mutableStateOf("")
     var password by mutableStateOf("")
    var isVisiblePaswd by mutableStateOf(false)



    fun changeVisi(value : Boolean): Boolean {
        return value != true
    }
    fun onLoginClicked() {
        // Aquí realizar la lógica de autenticación y/o comunicarte con la API
    }

    fun validEmail(){

    }

    fun validPassword(){

    }


}