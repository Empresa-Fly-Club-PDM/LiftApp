package com.jder00138218.liftapp.ui.recovery.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.jder00138218.liftapp.LiftAppApplication
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.network.ApiResponse
import com.jder00138218.liftapp.repositories.CredentialsRepository
import com.jder00138218.liftapp.ui.login.LoginUiStatus
import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.ui.recovery.RecoveryUiStatus
import com.jder00138218.liftapp.ui.register.RegisterUiStatus
import kotlinx.coroutines.launch

class RecoveryViewModel(private val repository: CredentialsRepository) : ViewModel() {

    private var _email by mutableStateOf("")
    private var _status =  mutableStateOf<RecoveryUiStatus>(RecoveryUiStatus.Resume)


    val status: State<RecoveryUiStatus>
        get() = _status

    var email: String
        get() = _email
        set(value) {
            _email = value
        }

    private fun validateData(): Boolean {
        when {
            email.isEmpty() -> return false
        }
        return true
    }

    fun clearData() {
        _email = ""
    }

    fun clearStatus() {
        _status.value = RecoveryUiStatus.Resume
    }

    fun onRecovery(navController: NavHostController, context: Context) {
        if (!validateData()) {
            _status.value = RecoveryUiStatus.ErrorWithMessage(context.getString(R.string.wrong_imformation))
            Toast.makeText(context, context.getString(R.string.verificar_campos), Toast.LENGTH_SHORT).show()
            return
        }

        recovery(email, navController, context)
    }



    fun handleUiStatus(navController: NavHostController, context: Context) {
        // val status = _status.value

        when (_status.value) {

            is RecoveryUiStatus.Error -> {

                Toast.makeText(context, context.getString(R.string.error_en_recuperar), Toast.LENGTH_SHORT).show()
            }

            is RecoveryUiStatus.ErrorWithMessage -> {
                Toast.makeText(context, context.getString(R.string.datos_no_validos), Toast.LENGTH_SHORT).show()
            }

            is RecoveryUiStatus.Success -> {
                Toast.makeText(context, context.getString(R.string.correo_verificado), Toast.LENGTH_SHORT).show()
                Toast.makeText(
                    context,
                    context.getString(R.string.hemos_enviado_la_informacion_a_su_correo_electronico),
                    Toast.LENGTH_SHORT
                ).show()
                clearStatus()
                clearData()
                navController.navigate(route = Rutas.Login.ruta)
            }

            else -> {}
        }
    }

    private fun recovery(email: String, navController: NavHostController, context: Context) {

        viewModelScope.launch {
            _status.value = (
                    when (val response =
                        repository.recovery(email)) {
                        is ApiResponse.Error -> RecoveryUiStatus.Error(response.exception)
                        is ApiResponse.ErrorWithMessage -> RecoveryUiStatus.ErrorWithMessage(
                            response.message
                        )

                        is ApiResponse.Success -> RecoveryUiStatus.Success
                    }
                    )
            handleUiStatus(navController, context)
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as LiftAppApplication
                RecoveryViewModel(app.credentialsRepository)
            }
        }
    }
}