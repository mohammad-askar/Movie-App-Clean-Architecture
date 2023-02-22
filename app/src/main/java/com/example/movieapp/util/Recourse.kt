package com.example.movieapp.util

sealed class Recourse<T>(
    val data: T?,
    val error: Throwable? = null
) {
    class Success<T>(data: T): Recourse<T>(data = data)
    class Loading<T>(data: T?): Recourse<T>(data = data)
    class Error<T>(data: T?, error: Throwable? = null): Recourse<T>(data = data, error = error)

}