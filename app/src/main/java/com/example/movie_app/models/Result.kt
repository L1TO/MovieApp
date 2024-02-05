package com.example.movie_app.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.movie_app.utils.IntListConverter
import java.io.Serializable

@Entity(tableName = "movie_table")
@TypeConverters(IntListConverter::class)
data class Result(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val adult: Boolean?,
    val backdrop_path: String?,
    val genre_ids: List<Int>?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?
): Serializable