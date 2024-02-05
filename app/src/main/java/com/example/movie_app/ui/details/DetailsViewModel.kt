package com.example.movie_app.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_app.data.database.repository.MoviesRepositoryRealisation
import com.example.movie_app.models.Result
import com.example.movie_app.utils.REALIZATION
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel: ViewModel() {

    fun insertToDatabase(movieItemModel: Result, onSuccess:() -> Unit) =
        viewModelScope.launch {
            REALIZATION.insertMovie(movieItemModel) {
                onSuccess()
            }
        }

    fun deleteFromDatabase(movieItemModel: Result, onSuccess:() -> Unit) =
        viewModelScope.launch {
            REALIZATION.deleteMovie(movieItemModel) {
                onSuccess()
            }
        }
}