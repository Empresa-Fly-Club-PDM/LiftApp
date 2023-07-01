package com.jder00138218.liftapp.network.dto.register

data class RegisterRequest(
    val nombrecompleto: String,
    val email: String,
    val password: String,
    val genero: String,
    val weigth: Double,
    val height: Double,
    val fechanac: String,
)