package com.jder00138218.liftapp.network.retrofit

import com.jder00138218.liftapp.network.services.AuthService
import com.jder00138218.liftapp.network.services.ExerciseService
import com.jder00138218.liftapp.network.services.LiftService
import com.jder00138218.liftapp.network.services.RoutineService
import com.jder00138218.liftapp.network.services.UserService
import com.jder00138218.liftapp.network.services.VerifyDenyExerciseService
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

const val BASE_URL = "https://liftapp.pro/"
object RetrofitInstance {

    private  var token = ""

    fun setToken(token: String){
        this.token = token
    }


    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient.Builder()
                .addInterceptor{chain->
                    chain.proceed(chain.request().newBuilder().also{
                        it.addHeader("Authorization", "Bearer $token")
                    }.build())
                }.build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getLoginService(): AuthService{
        return retrofit.create(AuthService::class.java)
    }

    fun getExerciseService():ExerciseService{
        return retrofit.create(ExerciseService::class.java)
    }
    fun getVerifyDenyExerciseService():VerifyDenyExerciseService{
        return retrofit.create(VerifyDenyExerciseService::class.java)
    }

    fun getUserService():UserService{
        return retrofit.create(UserService::class.java)
    }

    fun getLiftService():LiftService{
        return retrofit.create(LiftService::class.java)
    }

    fun getRoutineService():RoutineService{
        return retrofit.create(RoutineService::class.java)
    }

}