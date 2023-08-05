package com.example.movizephyr

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.movizephyr.adaptor.movie.SearchMovieByIdRecyclerViewAdaptor
import com.example.movizephyr.databinding.FragmentInfoBinding
import com.example.movizephyr.endpoints.movies.search.SearchByMovieId
import com.example.movizephyr.modal.RetrofitInstance
import com.example.movizephyr.modal.movies.search.byid.SearchMoviesId
import okhttp3.internal.toImmutableList
import retrofit2.Response

class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding
    private lateinit var sp : SharedPreferences
    private lateinit var retSearchByMovieId: SearchByMovieId
    private lateinit var movie_id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        retSearchByMovieId = RetrofitInstance.getRetrofitInstance().create( SearchByMovieId::class.java)

        showMovieInfoInDetailForReservation()

        return binding.root
    }

    private fun showMovieInfoInDetailForReservation(){

        sp = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        movie_id = sp.getString("movie_id", "") ?: ""

        val responseLiveData: LiveData<Response<SearchMoviesId>> = liveData {
            val response =
                retSearchByMovieId.getSearchByMoviesId("Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4OTdkNGZlOTFlZGViZmJlODNiMzAzYjdkZTA3ODRiOSIsInN1YiI6IjY0YjNjOTlkMjNkMjc4MDBjOTNjNDdlYiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.t4r-V0rOgS0n2DhOMd-Gd8E61jefu_fC-0FAjozOvaw",movie_id)
            emit(response)
        }
        responseLiveData.observe(this, Observer {
            val movieById = it.body()
            if (movieById != null) {
                Log.i("sick", movieById.toString())
                binding.btnRating.text = "IMBD "+ movieById.vote_average
                binding.ratingValue.text = movieById.vote_count.toString()
                binding.infoMovieTitle.text = movieById.title
                binding.infoMovieDescription.text = movieById.overview
                binding.status.text = movieById.status
                Glide.with(this.context)
                    .load("https://image.tmdb.org/t/p/w500"+movieById.poster_path)
                    .into(binding.tvInfoMovieImg)
                val recyclerView = binding.infoMovieRecylerview
                recyclerView.setBackgroundColor(Color.TRANSPARENT)
                recyclerView.layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                val movieByIdGenre = movieById.genres!!.toImmutableList()
                if (movieByIdGenre != null) {
                    recyclerView.adapter =
                        SearchMovieByIdRecyclerViewAdaptor(movieByIdGenre, context!!)
                }
            }
        })

    }
}