package com.example.movizephyr

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movizephyr.adaptor.UpcomingRecyclerViewAdaptor
import com.example.movizephyr.databinding.FragmentHomeBinding
import com.example.movizephyr.endpoints.movies.UpcomingMovies
import com.example.movizephyr.modal.RetrofitInstance
import com.example.movizephyr.modal.movies.upcoming.UpComing
import okhttp3.internal.toImmutableList
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var retUpComing: UpcomingMovies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        retUpComing = RetrofitInstance.getRetrofitInstance().create(UpcomingMovies::class.java)

        val recyclerView = binding.upcomingRecyclerview
        recyclerView.setBackgroundColor(Color.TRANSPARENT)
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )

        val responseLiveData: LiveData<Response<UpComing>> = liveData {
            val response = retUpComing.getUpcomingMovies("Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4OTdkNGZlOTFlZGViZmJlODNiMzAzYjdkZTA3ODRiOSIsInN1YiI6IjY0YjNjOTlkMjNkMjc4MDBjOTNjNDdlYiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.t4r-V0rOgS0n2DhOMd-Gd8E61jefu_fC-0FAjozOvaw")
            emit(response)
        }
        responseLiveData.observe(this, Observer {
            val upcomingMoviesList = it.body()?.results?.toImmutableList()
            if (upcomingMoviesList != null) {
                recyclerView.adapter = UpcomingRecyclerViewAdaptor(upcomingMoviesList, context!!)
            }
        })

        return binding.root
    }

}