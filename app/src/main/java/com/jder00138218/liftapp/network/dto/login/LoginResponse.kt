package com.jder00138218.liftapp.network.dto.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("email") val email: String,
    @SerializedName("accesToken") val accesToken: String,
    @SerializedName("id") val id: Int
)

