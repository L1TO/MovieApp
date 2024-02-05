package com.example.movie_app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movie_app.data.database.dao.MovieDao
import com.example.movie_app.models.Result

@Database(entities = [Result::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getMovieDao(): MovieDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "database")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}