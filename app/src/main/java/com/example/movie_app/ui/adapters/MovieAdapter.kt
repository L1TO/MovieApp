package com.example.movie_app.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie_app.databinding.MovieItemLayoutBinding
import com.example.movie_app.models.Result

interface MovieActionListener {
    fun onMovieDetails(movie: Result, movieTitle: String)
}

class MovieAdapter(
    private val movieActionListener: MovieActionListener
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>(), OnClickListener {

    var movies: List<Result> = emptyList()
        set(value) {
            val diffCallback = MovieDiffUtilCallBack(field, value)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    class ViewHolder(
        val binding: MovieItemLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemLayoutBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener(this)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMovie = movies[position]
        holder.itemView.tag = currentMovie
        with(holder.binding) {
            movieTitleTv.text = currentMovie.title
            ratingTv.text = currentMovie.vote_average.toString()
            Glide.with(itemIv.context)
                .load("https://www.themoviedb.org/t/p/w300_and_h450_bestv2${currentMovie.poster_path}")
                .into(itemIv)
        }
    }

    override fun getItemCount() = movies.size

    override fun onClick(v: View) {
        val movie = v.tag as Result
        movieActionListener.onMovieDetails(movie, movie.title!!)

    }
}

class MovieDiffUtilCallBack(
    private val oldList: List<Result>,
    private val newList: List<Result>,
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}