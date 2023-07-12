package com.jder00138218.liftapp.ui.register.viewmodel

import android.content.Context
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.jder00138218.liftapp.LiftAppApplication
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.network.ApiResponse
import com.jder00138218.liftapp.repositories.CredentialsRepository

import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.ui.register.RegisterUiStatus
import kotlinx.coroutines.launch
import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class RegisterViewModel(private val repository: CredentialsRepository) : ViewModel() {
    private var _name by mutableStateOf("")
    private var _email by mutableStateOf("")
    private var _password by mutableStateOf("")
    private var _passwordVe by mutableStateOf("")
    private var _genre by mutableStateOf("")
    private var _date by mutableStateOf("")
    private var _weigth by mutableStateOf(0)
    private var _height by mutableStateOf(0)
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

    var heigth: Int
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
        heigth: Int,
        navController: NavHostController,
        context: Context
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
            handleUiStatus(navController, context)
        }
    }

    fun onRegister(navController: NavHostController, context: Context) {
        var years = 0

        if (date.isEmpty()) {
            years = 0
        } else {
            years = calcularEdad(date, context)
        }

        if (!validateData()) {
            _status.value = RegisterUiStatus.ErrorWithMessage(context.getString(R.string.wrong_imformation))
            Toast.makeText(context, context.getString(R.string.campos_invalidos), Toast.LENGTH_SHORT).show()
            when {
                (password != passwordVe) -> Toast.makeText(
                    context,
                    context.getString(R.string.error_contrase_as_no_coinciden),
                    Toast.LENGTH_SHORT
                ).show()


                (password.length < 8) -> Toast.makeText(
                    context,
                    context.getString(R.string.la_contrase_a_debe_ser_de_al_menos_8_caracteres),
                    Toast.LENGTH_SHORT
                ).show()

                (years < 13) -> Toast.makeText(
                    context,
                    context.getString(R.string.edad_invalida),
                    Toast.LENGTH_SHORT
                ).show()

                (!isValidEmail(email))->Toast.makeText(
                    context,
                    context.getString(R.string.formato_de_correo_incorrecto),
                    Toast.LENGTH_SHORT
                ).show()
            }

            return
        } else {
            register(name, email, password, genre, date, weigth, heigth, navController, context)
        }

    }

    private fun validateData(): Boolean {
        when {
            !isValidEmail(email) -> return false
            email.isEmpty() -> return false
            password.isEmpty() -> return false
            passwordVe.isEmpty() -> return false
            genre.isEmpty() -> return false
            date.isEmpty() -> return false
            (weigth == 0) -> return false
            (heigth == 0) -> return false
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
        _height = 0
        _weigth = 0
    }

    fun handleUiStatus(
        navController: NavHostController, context: Context
    ) {
        val status = _status.value

        when (status) {

            is RegisterUiStatus.Error -> {

                Toast.makeText(context, context.getString(R.string.error_en_registro), Toast.LENGTH_SHORT).show()
            }

            is RegisterUiStatus.ErrorWithMessage -> {
                Toast.makeText(context, context.getString(R.string.datos_no_validos), Toast.LENGTH_SHORT).show()
            }

            is RegisterUiStatus.Success -> {
                Toast.makeText(context, context.getString(R.string.usuario_creado_exitosamente), Toast.LENGTH_SHORT).show()
                clearStatus()
                clearData()
                navController.navigate(route = Rutas.Login.ruta)
            }

            else -> {}
        }
    }


    fun calcularEdad(fechaNacimiento: String,context: Context): Int {
        val formatoFecha = SimpleDateFormat(context.getString(R.string.dd_mm_yyyy), Locale.getDefault())
        val fechaNac = formatoFecha.parse(fechaNacimiento)
        val fechaActual = Date()

        val diff = fechaActual.time - fechaNac.time
        val edadMillis = abs(diff)

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = edadMillis

        val anio = calendar.get(Calendar.YEAR) - 1970
        val mes = calendar.get(Calendar.MONTH)
        val dia = calendar.get(Calendar.DAY_OF_MONTH)

        // Ajustar la edad si todavía no ha pasado el cumpleaños en el año actual
        if (mes < 0 || (mes == 0 && dia < 0)) {
            return anio - 1
        }

        return anio
    }


    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as LiftAppApplication
                RegisterViewModel(app.credentialsRepository)
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
}


