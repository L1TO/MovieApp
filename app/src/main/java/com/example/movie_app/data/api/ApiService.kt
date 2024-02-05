package com.example.movie_app.data.api

import com.example.movie_app.models.MovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("3/movie/popular?api_key=40f9bb4414f9f9baecc629bd6bc649a9")
    suspend fun getPopularMovies(
        @Query("language") language: String = "en",
        @Query("page") page: Int = 1,
    ): Response<MovieModel>

    @GET("3/search/movie?api_key=40f9bb4414f9f9baecc629bd6bc649a9")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
    ): Response<MovieModel>


}