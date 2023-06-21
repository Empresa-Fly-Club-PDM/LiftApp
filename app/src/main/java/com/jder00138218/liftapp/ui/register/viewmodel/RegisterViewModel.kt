package com.jder00138218.liftapp.ui.register.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jder00138218.liftapp.RetrofitApplication
import com.jder00138218.liftapp.repositories.CredentialsRepository

class RegisterViewModel(private val repository: CredentialsRepository) : ViewModel() {
    private var _name by mutableStateOf("")
    private var _email by mutableStateOf("")
    private var _password by mutableStateOf("")
    private var _genre by mutableStateOf("")
    private var _date by mutableStateOf("")
    private var _weigth by mutableStateOf(0)
    private var _height by mutableStateOf(0.0)
    private var _isVisiblePaswd by mutableStateOf(false)

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

    fun register(){

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