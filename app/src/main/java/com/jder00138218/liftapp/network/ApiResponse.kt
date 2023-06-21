package com.jder00138218.liftapp.network

sealed class ApiResponse<T>{

    data class Success<T>(val data: String) : ApiResponse<T>()



    data class Error<T>(val exception: Exception) : ApiResponse<T>()
    data class ErrorWithMessage<T>(val message: String) : ApiResponse<T>()
}