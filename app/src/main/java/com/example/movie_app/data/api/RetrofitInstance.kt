package com.example.movie_app.data.api

import com.example.movie_app.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiSource: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}