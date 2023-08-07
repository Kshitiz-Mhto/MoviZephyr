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
import com.example.movizephyr.adaptor.movie.WatchProviderRegionsRecyclerViewAdaptor
import com.example.movizephyr.databinding.FragmentReservationBinding
import com.example.movizephyr.endpoints.movies.WatchProvidersRegions
import com.example.movizephyr.modal.RetrofitInstance
import com.example.movizephyr.modal.movies.search.byid.SearchMoviesId
import com.example.movizephyr.modal.movies.watchproviders.WatchProviders
import okhttp3.internal.toImmutableList
import retrofit2.Response

class ReservationFragment : Fragment() {

    private lateinit var binding: FragmentReservationBinding
    private lateinit var retWatchProviderRegions: WatchProvidersRegions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReservationBinding.inflate(inflater, container, false)
        retWatchProviderRegions = RetrofitInstance.getRetrofitInstance().create(WatchProvidersRegions::class.java)

        showRegion()

        return binding.root
    }

    private fun showRegion() {
        val responseLiveData: LiveData<Response<WatchProviders>> = liveData {
            val response =
                retWatchProviderRegions.getWatchProviderRegion("Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4OTdkNGZlOTFlZGViZmJlODNiMzAzYjdkZTA3ODRiOSIsInN1YiI6IjY0YjNjOTlkMjNkMjc4MDBjOTNjNDdlYiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.t4r-V0rOgS0n2DhOMd-Gd8E61jefu_fC-0FAjozOvaw")
            emit(response)
        }
        responseLiveData.observe(this, Observer {
            val regions = it.body()?.results?.toImmutableList()
            if (regions != null) {
                val recyclerView = binding.regionRecyclerView
                recyclerView.setBackgroundColor(Color.TRANSPARENT)
                recyclerView.adapter = WatchProviderRegionsRecyclerViewAdaptor(regions, context!!)
            }
        })
    }

}