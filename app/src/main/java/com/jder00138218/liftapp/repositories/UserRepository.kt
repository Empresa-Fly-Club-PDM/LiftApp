package com.jder00138218.liftapp.repositories

import com.jder00138218.liftapp.data.db.models.UserModel
import com.jder00138218.liftapp.data.network.services.UserService

class UserRepository {

    private val api = UserService()




    suspend fun getAllUsers():List<UserModel>{
        val response  = api.getUsers()
        return response
    }


}