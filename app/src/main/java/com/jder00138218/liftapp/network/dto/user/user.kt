package com.jder00138218.liftapp.network.dto.user

data class user(
    val id: Int = 0,
    val nombrecompleto: String = "",
    val email: String = "",
    val password: String = "",
    val passwordState: Boolean = false,
    val genero: String = "",
    val fechanac: String = "",
    val weight: Int = 0,
    val height: Double = 0.0,
    val enabledstate: Boolean = false,
    val points: Int = 0,
    val role: String = "",
    val enabled: Boolean = false,
    val username: String = "",
    val authorities: List<authority> = emptyList(),
    val accountNonExpired: Boolean = false,
    val accountNonLocked: Boolean = false,
    val credentialsNonExpired: Boolean = false
)
