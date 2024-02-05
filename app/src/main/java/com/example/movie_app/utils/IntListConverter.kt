package com.example.movie_app.utils

import androidx.room.TypeConverter

class IntListConverter {

    @TypeConverter
    fun fromIntList(intList: List<Int>?): String? {
        return intList?.joinToString(",")
    }

    @TypeConverter
    fun toIntList(intListString: String?): List<Int>? {
        return intListString?.split(",")?.mapNotNull { it.toIntOrNull() }
    }
}