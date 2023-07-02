package com.jder00138218.liftapp.network.services

import com.jder00138218.liftapp.network.dto.exercise.PostVerifiedExerciseRequest
import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.network.dto.register.RegisterRequest
import com.jder00138218.liftapp.network.dto.user.PostUserRequest
import com.jder00138218.liftapp.network.dto.user.user
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    @GET("usuario/get/admins")
    suspend fun getAllAdmins(@Query("query") query:String): List<user>

    @GET("friends/listFriends/{id}")
    suspend fun getMyFriends(@Path("id") id:Int?): List<user>
    @GET("usuario/get/users/{id}")
    suspend fun searchForFriends(@Path("id") id:Int?,@Query("query") query:String): List<user>
    @GET("usuario/details/{id}")
    suspend fun getUserDetails(@Path("id") id:Int?): user
    @POST("usuario/add/admin")
    suspend fun createUser(@Body newUser: PostUserRequest): Response<Void>
    @PUT("usuario/edit/{id}")
    suspend fun editUser(@Body updatedUSer:PostUserRequest,@Path("id")id:Int?):Response<Void>

    @DELETE("usuario/delete/{id}")
    suspend fun deleteUser(@Path("id")id:Int?):Response<Void>
    @GET("friends/addFriend/{current}/{friend}")
    suspend fun addFriend(@Path("current") current:Int?,@Path("friend")friend:Int?):Response<Void>

    @GET("usuario/top5")
    suspend fun getRanking(@Query("query") query:String):List<user>

    @PUT("usuario/edit/{id}")
    suspend fun editMyprofile(@Body editUser: RegisterRequest,@Path("id") id: Int?):Response<Void>


}