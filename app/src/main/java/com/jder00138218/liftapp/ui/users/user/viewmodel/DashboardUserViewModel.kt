package com.jder00138218.liftapp.ui.users.user.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jder00138218.liftapp.LiftAppApplication
import com.jder00138218.liftapp.network.dto.user.user
import com.jder00138218.liftapp.repositories.UserRepository
import com.jder00138218.liftapp.ui.users.admin.adminProfile.viewmodel.AdminProfileViewModel
import kotlinx.coroutines.launch

class DashboardUserViewModel(private val userRepository: UserRepository): ViewModel() {
    private var _user = mutableStateOf<user>(user())

    val user: user
        get() = _user.value

    fun addExercise(newUser: user) {
        _user.value = newUser
    }

    fun getUserDetails(id: Int?) {
        viewModelScope.launch {
            addExercise(userRepository.getUserDetails(id))
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as LiftAppApplication
                DashboardUserViewModel(app.userRepository)
            }
        }
    }

}