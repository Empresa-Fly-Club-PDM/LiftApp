package com.jder00138218.liftapp.ui.users.user.ranking.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jder00138218.liftapp.LiftAppApplication
import com.jder00138218.liftapp.network.dto.user.user
import com.jder00138218.liftapp.repositories.UserRepository
import com.jder00138218.liftapp.ui.users.user.friends.viewmodel.FriendMenuViewModel
import kotlinx.coroutines.launch

class RankinViewModel(private val userRepository: UserRepository): ViewModel() {
    private val _users = mutableStateListOf<user>()

    val users: List<user>
        get() = _users

    fun getRanking(query:String) {
        viewModelScope.launch {
            _users.clear()
            _users.addAll(userRepository.getRanking(query))
        }
    }


    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as LiftAppApplication
                RankinViewModel(app.userRepository)
            }
        }
    }
}