package com.example.movizephyr

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.movizephyr.databinding.FragmentInfoBinding
import com.example.movizephyr.endpoints.movies.search.SearchByMovies
import com.example.movizephyr.modal.RetrofitInstance

class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding
    private lateinit var sp : SharedPreferences
    private lateinit var movieName: String
    private lateinit var rating: String
    private lateinit var rating_num: String
    private lateinit var movieUrl: String
    private lateinit var movieId: String
    private lateinit var descriptionOverview: String
    private lateinit var retSearchByMovieId: SearchByMovies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        retSearchByMovieId = RetrofitInstance.getRetrofitInstance().create(SearchByMovies::class.java)

        showMovieInfoInDetailForReservation()

        return binding.root
    }

    private fun showMovieInfoInDetailForReservation(){

        sp = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        movieName = sp.getString("movie_name", "") ?: ""
        rating = sp.getString("rating", "") ?: ""
        rating_num  = " \uD83C\uDF1F " +sp.getString("rating_num", "")
        movieUrl = sp.getString("movie_url", "") ?: ""
        descriptionOverview = sp.getString("description_overview", "") ?: ""
        movieId = sp.getString("movie_id", "") ?: ""
//        Log.i("mm", movieName)

        binding.btnRating.text = "IMBD "+rating
        binding.ratingValue.text = rating_num
        binding.infoMovieTitle.text = movieName
        binding.infoMovieDescription.text = descriptionOverview
        Glide.with(this.context)
            .load(movieUrl)
            .into(binding.tvInfoMovieImg)
    }
}