package com.example.movie_app.data.api

import com.example.movie_app.models.MovieModel
import retrofit2.Response

class RetrofitRepository {
    suspend fun getMovies(): Response<MovieModel> {
        return RetrofitInstance.apiSource.getPopularMovies()
    }
    suspend fun searchMovies(query: String): Response<MovieModel> {
        return RetrofitInstance.apiSource.searchMovies(query = query)
    }
}