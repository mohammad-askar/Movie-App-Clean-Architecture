package com.example.movieapp.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class RepositoryHelper {

    suspend fun <T>invokeApi(
        apiCall: suspend () -> T
    ): Recourse<T>{
        return withContext(Dispatchers.IO){
            try {
                Recourse.Success(apiCall.invoke())
            }catch (t: Throwable){
                Recourse.Error(data = null, error = t)
            }
        }
    }
}