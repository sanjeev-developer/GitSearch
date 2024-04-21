package com.git.gitsearch.network

sealed class ApiState<T>(val data: T? = null, val errorMessage:String? = null) {
    class Loading<T>: ApiState<T>()
    class Success<T>(data: T? = null): ApiState<T>(data = data)
    class Failure<T>(errorMessage: String? = null): ApiState<T>(errorMessage = errorMessage)
}