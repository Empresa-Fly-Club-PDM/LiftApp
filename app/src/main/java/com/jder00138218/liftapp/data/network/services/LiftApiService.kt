package com.jder00138218.liftapp.data.network.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
* Conexion a API
* */
class LiftApiService {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://localhost:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}