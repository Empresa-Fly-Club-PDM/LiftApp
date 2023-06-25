package com.jder00138218.liftapp.ui.users.admin.DasboardAdminViewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jder00138218.liftapp.RetrofitApplication
import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.network.retrofit.RetrofitInstance
import com.jder00138218.liftapp.network.services.ExerciseService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardAdminViewmodel (): ViewModel(){
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
            _exercises.addAll(exerciseService.getSolicitudes(query))
    }
}
}