package com.example.movie_app.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movie_app.databinding.FragmentSearchBinding
import com.example.movie_app.models.Result
import com.example.movie_app.ui.adapters.MovieActionListener
import com.example.movie_app.ui.adapters.MovieAdapter
import com.example.movie_app.ui.main.MainFragmentDirections

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<SearchViewModel>()
    private val recyclerAdapter by lazy {
        MovieAdapter(object : MovieActionListener {
            override fun onMovieDetails(movie: Result, movieTitle: String) {
                val directions = SearchFragmentDirections.actionSearchFragmentToDetailsFragment(
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
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.recycler.adapter = recyclerAdapter
        binding.recycler.layoutManager = GridLayoutManager(this.context, 2)
        binding.textInputEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                viewModel.searchMoviesFromApi(binding.textInputEditText.text.toString())
            }

        })
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            recyclerAdapter.movies = it.body()?.results ?: return@Observer
        })
    }
}