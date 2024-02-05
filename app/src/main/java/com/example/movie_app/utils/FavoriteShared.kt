package com.example.movie_app.utils

import android.content.Context
import android.preference.PreferenceManager

class FavoriteShared {
    companion object {
        fun setFavoriteShared(context: Context?, key: String, value: Boolean) {
            val setFavoriteShared = PreferenceManager.getDefaultSharedPreferences(context)
            setFavoriteShared.edit().putBoolean(key, value).apply()
        }

        fun getFavoriteShared(context: Context?, key: String) : Boolean {
            val setFavoriteShared = PreferenceManager.getDefaultSharedPreferences(context)
            return setFavoriteShared.getBoolean(key, false)
        }
    }
}