package com.example.movie_app.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_app.R
import com.example.movie_app.databinding.FragmentDetailsBinding
import com.example.movie_app.databinding.FragmentFavoriteBinding
import com.example.movie_app.models.Result
import com.example.movie_app.ui.adapters.MovieActionListener
import com.example.movie_app.ui.adapters.MovieAdapter
import com.example.movie_app.ui.details.DetailsViewModel
import com.example.movie_app.ui.main.MainFragmentDirections

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val recyclerAdapter by lazy {
        MovieAdapter(object : MovieActionListener {
            override fun onMovieDetails(movie: Result, movieTitle: String) {
                val directions = FavoriteFragmentDirections.actionFavoriteFragmentToDetailsFragment(
                    movie,
                    movie.title!!
                )
                findNavController().navigate(directions)
            }

        })
    }
    private val viewModel by viewModels<FavoriteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        viewModel.getMoviesFromDatabase().observe(viewLifecycleOwner) {
            recyclerAdapter.movies = it.asReversed()
        }
        binding.include.recycler.adapter = recyclerAdapter
        binding.include.recycler.layoutManager = LinearLayoutManager(this.context)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}