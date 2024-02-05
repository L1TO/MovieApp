package com.example.movie_app.ui.favorite

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movie_app.data.database.repository.MoviesRepositoryRealisation
import com.example.movie_app.models.MovieModel
import com.example.movie_app.models.Result
import com.example.movie_app.utils.REALIZATION
import retrofit2.Response

class FavoriteViewModel: ViewModel() {
    fun getMoviesFromDatabase(): LiveData<List<Result>> = REALIZATION.allMovies

}