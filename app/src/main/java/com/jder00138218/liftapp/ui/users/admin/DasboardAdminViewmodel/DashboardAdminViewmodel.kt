package com.jder00138218.liftapp.ui.users.admin.DasboardAdminViewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jder00138218.liftapp.RetrofitApplication
import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.network.retrofit.RetrofitInstance
import com.jder00138218.liftapp.network.services.ExerciseService
import com.jder00138218.liftapp.repositories.CredentialsRepository
import com.jder00138218.liftapp.repositories.ExerciseRepository
import com.jder00138218.liftapp.ui.login.viewmodel.LoginViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardAdminViewmodel (private val exerciseRepository: ExerciseRepository): ViewModel(){
    private val _exercises = mutableStateListOf<exercise>()
    val exercises: List<exercise>
        get() = _exercises

    private val exerciseService = RetrofitInstance.getExerciseService()
/*
    fun fetchExercises(query:String) {
        exerciseService.getSolicitudes(token,query).enqueue(object : Callback<List<exercise>> {
            override fun onResponse(call: Call<List<exercise>>, response: Response<List<exercise>>) {
                if (response.isSuccessful) {
                    _exercises.value = response.body()
                }
            }
            override fun onFailure(call: Call<List<exercise>>, t: Throwable) {
                // Handle error
            }
        })
    }*/
fun getSolicitudes(query:String) {
    viewModelScope.launch {
            _exercises.clear()
            _exercises.addAll(exerciseRepository.getSolicitudes(query))
    }
}

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as RetrofitApplication
                DashboardAdminViewmodel(app.exerciseRepository)
            }
        }
    }
}