package com.jder00138218.liftapp

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.jder00138218.liftapp.network.retrofit.RetrofitInstance
import com.jder00138218.liftapp.repositories.CredentialsRepository
import com.jder00138218.liftapp.repositories.ExerciseRepository

class RetrofitApplication: Application() {
    private val prefs: SharedPreferences by lazy {
        getSharedPreferences("Retrofit", Context.MODE_PRIVATE)
    }

    //Servicio para login
    private fun getApiService() = with(RetrofitInstance){
        setToken(getToken())
        getLoginService()
    }

    //Servicio para ejercicios
    private fun getExerciseService()= with(RetrofitInstance){
        setToken(getToken())
        getExerciseService()
    }


    fun getToken(): String = prefs.getString(USER_TOKEN, "")!!

    val credentialsRepository: CredentialsRepository by lazy {
        CredentialsRepository(getApiService())
    }

    val exerciseRepository: ExerciseRepository by lazy{
        ExerciseRepository(getExerciseService())
    }

    fun saveAuthToken(token: String){
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    companion object{
        private const val USER_TOKEN = "user_token"
    }
}