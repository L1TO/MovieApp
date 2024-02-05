package com.example.movie_app.data.database.repository

import androidx.lifecycle.LiveData
import com.example.movie_app.data.database.dao.MovieDao
import com.example.movie_app.models.Result

class MoviesRepositoryRealisation(
    private val movieDao: MovieDao
): MoviesRepository {
    override val allMovies: LiveData<List<Result>>
        get() = movieDao.getFavoriteMovies()

    override suspend fun insertMovie(movieItemModel: Result, onSuccess: () -> Unit) {
        movieDao.insert(movieItemModel)
        onSuccess()
    }

    override suspend fun deleteMovie(movieItemModel: Result, onSuccess: () -> Unit) {
        movieDao.delete(movieItemModel)
        onSuccess()
    }


}