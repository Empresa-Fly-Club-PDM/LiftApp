package com.jder00138218.liftapp.data.network.services

import com.jder00138218.liftapp.data.db.models.UserModel
import com.jder00138218.liftapp.data.network.clients.UsersApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserService {
    private val retrofit = LiftApiService().getRetrofit()
     suspend fun getUsers():List<UserModel>{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(UsersApiClient::class.java).getUsers()
            response.body() ?: emptyList()
        }
     }
}