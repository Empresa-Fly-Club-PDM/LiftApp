package com.jder00138218.liftapp.ui.users.user.userhistory.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jder00138218.liftapp.LiftAppApplication
import com.jder00138218.liftapp.network.dto.lift.lift
import com.jder00138218.liftapp.network.dto.user.user
import com.jder00138218.liftapp.repositories.LiftRepository
import com.jder00138218.liftapp.ui.users.admin.userManager.AdminManagement.viewmodel.AdminManagementViewModel
import kotlinx.coroutines.launch

class UserHistoryViewModel(private val liftRepository: LiftRepository) : ViewModel(){
    private val _lifts = mutableStateListOf<lift>()
    val lifts: List<lift>
        get() = _lifts

    fun getMyLifts(id: Int?, query:String) {
        viewModelScope.launch {
            _lifts.clear()
            _lifts.addAll(liftRepository.getMyLifts(id,query))
        }
    }




    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as LiftAppApplication
                UserHistoryViewModel(app.liftRepository)
            }
        }
    }

}