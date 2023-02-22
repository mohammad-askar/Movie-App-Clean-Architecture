package com.example.movieapp.util

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ApiBuilder @Inject constructor() {

    fun <Api> builder(api: Class<Api>): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getClint())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

    private fun getClint(): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor{ chain ->
                chain.proceed(
                    chain.request().newBuilder()
                        .also { requestBuilder ->
                        requestBuilder.addHeader("X-Requested-With", "XMLHttpRequest")
                        requestBuilder.addHeader("content-type", "application/json")
                    }.build()
                )
                
            }.also { client ->
                client.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MINUTES)
                    .readTimeout(READ_TIMEOUT, TimeUnit.MINUTES)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            }.build()
    }
}