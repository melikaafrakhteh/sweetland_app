package com.afrakhteh.sweetlandapp.util

sealed class NetworkResponse<T>(
    val data: List<T>? = null,
    val message: String? = null
) {
    class Loading<T>(data: List<T>? = null) : NetworkResponse<T>(data)
    class Error<T>(data: List<T>? = null, message: String) : NetworkResponse<T>(data, message)
    class Success<T>(listOfData: List<T>, message: String? = null) :
        NetworkResponse<T>(listOfData, message)
}
