package com.jder00138218.liftapp.ui.users.user.findfriends.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavController
import com.jder00138218.liftapp.LiftAppApplication
import com.jder00138218.liftapp.network.ApiResponse
import com.jder00138218.liftapp.network.dto.user.user
import com.jder00138218.liftapp.repositories.UserRepository
import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.ui.users.user.addexercisetoroutine.AddExerciseToRoutineUIStatuss
import com.jder00138218.liftapp.ui.users.user.findfriends.FindFriendsUIStatus
import com.jder00138218.liftapp.ui.users.user.friends.viewmodel.FriendMenuViewModel
import kotlinx.coroutines.launch

class FindFriendsViewModel(private val userRepository: UserRepository):ViewModel() {
    private val _users = mutableStateListOf<user>()
    val _status = MutableLiveData<FindFriendsUIStatus>(FindFriendsUIStatus.Resume)

    val users: List<user>
        get() = _users

    fun searchForFriends(id:Int?,query:String) {
        viewModelScope.launch {
            _users.clear()
            _users.addAll(userRepository.searchForfriend(id,query))
        }
    }

    fun adFriend(userid:Int?, friendid:Int?, navController: NavController, context: Context) {
        viewModelScope.launch {
            _status.value = (
                    when (val response = userRepository.addFriend(userid,friendid)) {
                        is ApiResponse.Error -> FindFriendsUIStatus.Error(response.exception)
                        is ApiResponse.ErrorWithMessage -> FindFriendsUIStatus.ErrorWithMessage(response.message)
                        is ApiResponse.Success -> FindFriendsUIStatus.Success(
                            response.data
                        )
                    }
                    )
            Toast.makeText(context, "Amigo Añadido", Toast.LENGTH_SHORT).show()
            navController.navigate(route= Rutas.FriendsMenu.ruta)
        }
    }


    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as LiftAppApplication
                FindFriendsViewModel(app.userRepository)
            }
        }
    }
}