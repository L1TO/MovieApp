package com.example.movie_app.ui.search

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_app.data.api.RetrofitRepository
import com.example.movie_app.models.MovieModel
import kotlinx.coroutines.launch
import retrofit2.Response

class SearchViewModel(

) : ViewModel() {
    private val repository = RetrofitRepository()
    val movies: MutableLiveData<Response<MovieModel>> = MutableLiveData()

    fun searchMoviesFromApi(query: String) {
        viewModelScope.launch {
            movies.value = repository.searchMovies(query)
        }
    }

}