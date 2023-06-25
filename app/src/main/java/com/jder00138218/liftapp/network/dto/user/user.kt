package com.jder00138218.liftapp.network.dto.user

data class user(
    val id: Int,
    val nombrecompleto: String,
    val email: String,
    val password: String,
    val passwordState: Boolean,
    val genero: String,
    val fechanac: String,
    val weight: Int,
    val height: Double,
    val enabledstate: Boolean,
    val points: Int,
    val role: String,
    val enabled: Boolean,
    val username: String,
    val authorities: List<authority>,
    val accountNonExpired: Boolean,
    val accountNonLocked: Boolean,
    val credentialsNonExpired: Boolean
)
