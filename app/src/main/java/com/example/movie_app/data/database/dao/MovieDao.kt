package com.example.movie_app.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movie_app.models.Result

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movieItemModel: Result)

    @Delete
    suspend fun delete(movieItemModel: Result)

    @Query("SELECT * FROM movie_table")
    fun getFavoriteMovies(): LiveData<List<Result>>
}