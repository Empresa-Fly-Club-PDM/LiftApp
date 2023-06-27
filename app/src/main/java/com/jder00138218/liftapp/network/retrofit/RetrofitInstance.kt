package com.jder00138218.liftapp.network.retrofit

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.ui.platform.LocalContext
import com.jder00138218.liftapp.RetrofitApplication
import com.jder00138218.liftapp.network.services.AuthService
import com.jder00138218.liftapp.network.services.ExerciseService
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

    class NullOnEmptyConverterFactory : Converter.Factory() {
        override fun responseBodyConverter(
            type: Type,
            annotations: Array<out Annotation>,
            retrofit: Retrofit
        ): Converter<ResponseBody, *> {
            val delegate: Converter<ResponseBody, *> = retrofit.nextResponseBodyConverter<Any?>(
                this, type, annotations
            )
            return Converter<ResponseBody, Any?> { body ->
                when (body.contentLength() == 0L) {
                    true -> null
                    else -> delegate.convert(body)
                }
            }
        }
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

}