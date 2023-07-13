package com.jder00138218.liftapp

import SessionManager
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.jder00138218.liftapp.network.retrofit.RetrofitInstance
import com.jder00138218.liftapp.repositories.CredentialsRepository
import com.jder00138218.liftapp.repositories.DetailExerciseRepository
import com.jder00138218.liftapp.repositories.ExerciseRepository
import com.jder00138218.liftapp.repositories.LiftRepository
import com.jder00138218.liftapp.repositories.RoutineRepository
import com.jder00138218.liftapp.repositories.UserRepository
import retrofit2.Retrofit

class   LiftAppApplication: Application() {
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

    private fun getVerifyDenyExerciseService()= with(RetrofitInstance){
        setToken(getToken())
        getVerifyDenyExerciseService()
    }

    private fun getUserRepository()= with(RetrofitInstance){
        setToken(getToken())
        getUserService()
    }

    private fun getLiftRepoistory()=with(RetrofitInstance){
        setToken(getToken())
        getLiftService()
    }


    private fun getRoutineService()=with(RetrofitInstance){
        setToken(getToken())
        getRoutineService()
    }

    fun getToken(): String = prefs.getString(USER_TOKEN, "b")!!

    fun getUserId():Int? = USER_ID

    val credentialsRepository: CredentialsRepository by lazy {
        CredentialsRepository(getApiService())
    }

    val detailExerciseRepository: DetailExerciseRepository by lazy {
        DetailExerciseRepository(getVerifyDenyExerciseService())
    }

    val exerciseRepository: ExerciseRepository by lazy{
        ExerciseRepository(getExerciseService())
    }

    val userRepository:UserRepository by lazy{
        UserRepository(getUserRepository())
    }

    val liftRepository:LiftRepository by lazy{
        LiftRepository(getLiftRepoistory())
    }

    val routineRepository:RoutineRepository by lazy{
        RoutineRepository(getRoutineService())
    }


    fun saveAuthToken(token: String){
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }
    val sessionManager: SessionManager by lazy {
        SessionManager(applicationContext)
    }

    fun saveUserID(id:Int?){
        USER_ID = id
    }

    companion object{
        private const val USER_TOKEN = "user_token"
        private var USER_ID:Int?=0
    }
}