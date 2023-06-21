package com.jder00138218.liftapp.network.dto.register

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val genre: String,
    val date: String,
    val weigth: Int,
    val height: Double
)