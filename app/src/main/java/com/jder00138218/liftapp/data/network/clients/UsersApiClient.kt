package com.jder00138218.liftapp.data.network.clients

import com.jder00138218.liftapp.data.db.models.UserModel
import retrofit2.Response

import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT

/*
* This code defines an interface called Retrofit
* that uses to make HTTP requests to the LiftAPI for users.
* */
interface UsersApiClient {

    @GET("/usuario/get")
    suspend fun getUsers() : Response<List<UserModel>>

    @PUT("usuario/edit}")
    suspend fun updateUser(){

    }

    @DELETE("/usuario/delete")
    suspend fun deleteUser(){

    }

    @PUT("/usuario/recovery")
    suspend fun resetPassword(){

    }

}