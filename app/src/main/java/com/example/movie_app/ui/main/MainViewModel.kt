package com.example.movie_app.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_app.data.api.RetrofitRepository
import com.example.movie_app.data.database.AppDatabase
import com.example.movie_app.data.database.repository.MoviesRepository
import com.example.movie_app.data.database.repository.MoviesRepositoryRealisation
import com.example.movie_app.models.MovieModel
import com.example.movie_app.utils.REALIZATION
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(
    private val application: Application
) : AndroidViewModel(application) {
    private val repository = RetrofitRepository()
    val movies: MutableLiveData<Response<MovieModel>> = MutableLiveData()
    val context = application

    private fun getMoviesFromApi() {
        viewModelScope.launch {
            movies.value = repository.getMovies()
        }
    }
    fun initDatabase() {
        val movieDao = AppDatabase.getInstance(context).getMovieDao()
        REALIZATION = MoviesRepositoryRealisation(movieDao)
    }

    init {
        getMoviesFromApi()
    }
}