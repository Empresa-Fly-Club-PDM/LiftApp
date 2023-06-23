package com.jder00138218.liftapp.network.retrofit

import android.content.Context
import android.content.SharedPreferences
import com.jder00138218.liftapp.network.services.AuthService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://liftapp.pro/"
object RetrofitInstance {

    private  var token = ""

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