package com.jder00138218.liftapp.network.retrofit

import android.content.Context
import android.content.SharedPreferences
import com.jder00138218.liftapp.network.services.AuthService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private  var token = ""
    private val BASE_URL = "http://localhost:8080"


    fun setToken(token: String){
        this.token = token
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getLoginService(): AuthService{
        return retrofit.create(AuthService::class.java)
    }

}