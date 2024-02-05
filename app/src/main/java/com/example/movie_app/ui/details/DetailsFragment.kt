package com.example.movie_app.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.movie_app.databinding.FragmentDetailsBinding
import com.example.movie_app.utils.FavoriteShared
import com.example.movie_app.utils.GENRES_NAMES

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: DetailsFragmentArgs by navArgs()
    private val viewModel by viewModels<DetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() = with(binding) {
        val currentMovie = args.movie
        val sharedFavorite = FavoriteShared.getFavoriteShared(context, currentMovie.id.toString())
        addToFavorite.isChecked = sharedFavorite

        Glide.with(requireContext())
            .load("https://www.themoviedb.org/t/p/w300_and_h450_bestv2${currentMovie.poster_path}")
            .into(detailsPosterIv)
        Glide.with(requireContext())
            .load("https://www.themoviedb.org/t/p/w1920_and_h800_multi_faces/${currentMovie.backdrop_path}")
            .into(detailsBackIv)
        titleTv.text = currentMovie.title
        releaseDateTv.text = currentMovie.release_date
        ratingTv.text = currentMovie.vote_average.toString()
        descriptionTv.text = currentMovie.overview
        val genreNames = currentMovie.genre_ids?.mapNotNull { genreId -> GENRES_NAMES[genreId] }
        genresInfoTv.text = genreNames.toString()

        addToFavorite.setOnClickListener {
            if (!addToFavorite.isChecked){
                FavoriteShared.setFavoriteShared(context, currentMovie.id.toString(), false)
                viewModel.deleteFromDatabase(currentMovie) {
                    showToast("Remove from favorite")
                }
            }
            else if(addToFavorite.isChecked){
                FavoriteShared.setFavoriteShared(context, currentMovie.id.toString(), true)
                viewModel.insertToDatabase(currentMovie) {
                    showToast("Add to favorite")
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}