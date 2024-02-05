package com.example.movie_app.data.database.repository

import androidx.lifecycle.LiveData
import com.example.movie_app.models.Result

interface MoviesRepository {
    val allMovies: LiveData<List<Result>>
    suspend fun insertMovie(movieItemModel: Result, onSuccess:() -> Unit)
    suspend fun deleteMovie(movieItemModel: Result, onSuccess:() -> Unit)
}