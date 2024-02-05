package com.example.movie_app.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie_app.R
import com.example.movie_app.databinding.FragmentMainBinding
import com.example.movie_app.models.Result
import com.example.movie_app.ui.adapters.MovieActionListener
import com.example.movie_app.ui.adapters.MovieAdapter

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<MainViewModel>()
    private val recyclerAdapter by lazy {
        MovieAdapter(object : MovieActionListener {
            override fun onMovieDetails(movie: Result, movieTitle: String) {
                val directions = MainFragmentDirections.actionMainFragmentToDetailsFragment(
                    movie,
                    movie.title!!
                )
                findNavController().navigate(directions)
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.recycler.adapter = recyclerAdapter
        binding.recycler.layoutManager = GridLayoutManager(this.context, 2)
        viewModel.initDatabase()
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            recyclerAdapter.movies = it.body()?.results ?: return@Observer
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}