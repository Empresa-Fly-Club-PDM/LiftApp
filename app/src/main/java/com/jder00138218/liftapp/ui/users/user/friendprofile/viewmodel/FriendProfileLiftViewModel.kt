package com.jder00138218.liftapp.ui.users.user.friendprofile.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jder00138218.liftapp.LiftAppApplication
import com.jder00138218.liftapp.network.dto.lift.lift
import com.jder00138218.liftapp.repositories.LiftRepository
import kotlinx.coroutines.launch

class FriendProfileLiftViewModel(private val liftRepository: LiftRepository): ViewModel() {
    private var _lift = mutableStateOf<lift?>(lift())

    val lift: lift?
        get() = _lift.value

    fun addExercise(newLift: lift?) {
        _lift.value = newLift
    }

    fun getUserBestLift(id: Int?) {
        viewModelScope.launch {
            addExercise(liftRepository.getMyHighlight(id))
        }
    }
    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as LiftAppApplication
                FriendProfileLiftViewModel(app.liftRepository)
            }
        }
    }
}