package com.example.movizephyr

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.movizephyr.adaptor.movie.NowPlayingRecyclerViewAdaptor
import com.example.movizephyr.adaptor.movie.PopularRecyclerViewAdaptor
import com.example.movizephyr.adaptor.movie.SearchRecyclerViewAdaptor
import com.example.movizephyr.adaptor.movie.TopRatedRecyclerViewAdaptor
import com.example.movizephyr.adaptor.movie.UpcomingRecyclerViewAdaptor
import com.example.movizephyr.databinding.FragmentHomeBinding
import com.example.movizephyr.endpoints.movies.NowPlayingMovies
import com.example.movizephyr.endpoints.movies.PopularMovies
import com.example.movizephyr.endpoints.movies.TopRatedMovies
import com.example.movizephyr.endpoints.movies.UpcomingMovies
import com.example.movizephyr.endpoints.movies.search.SearchByMovies
import com.example.movizephyr.modal.RetrofitInstance
import com.example.movizephyr.modal.movies.nowplaying.NowPlaying
import com.example.movizephyr.modal.movies.popular.Popular
import com.example.movizephyr.modal.movies.search.byname.SearchMovieName
import com.example.movizephyr.modal.movies.toprated.TopRated
import com.example.movizephyr.modal.movies.upcoming.UpComing
import kotlinx.coroutines.NonDisposableHandle.parent
import okhttp3.internal.toImmutableList
import retrofit2.Response
import retrofit2.create
import java.util.Locale

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var retUpComing: UpcomingMovies
    private lateinit var retNowPlaying: NowPlayingMovies
    private lateinit var retPopular: PopularMovies
    private lateinit var retTopRated: TopRatedMovies

    private  lateinit var sp: SharedPreferences

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var btnProfile: ImageButton
    private lateinit var navigationMenu: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        retUpComing = RetrofitInstance.getRetrofitInstance().create(UpcomingMovies::class.java)
        retNowPlaying = RetrofitInstance.getRetrofitInstance().create(NowPlayingMovies::class.java)
        retPopular = RetrofitInstance.getRetrofitInstance().create(PopularMovies::class.java)
        retTopRated = RetrofitInstance.getRetrofitInstance().create(TopRatedMovies::class.java)
        sp = context!!.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        upcomingMovies()
        nowplayingMovies()
        popularMovies()
        topratedMovies()

        binding.searchMe.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchMe.clearFocus()

                val searchText = query!!.lowercase(Locale.getDefault())

                if (searchText.isNotEmpty() or searchText.isNotBlank()){
                    val editor = sp.edit()
                    editor.putString("searched_movie_name", searchText)
                    editor.apply()
                    findNavController().navigate(
                        R.id.action_homeFragment_to_searchViewFragment
                    )
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        drawerLayout = binding.drawer
        btnProfile = binding.btnProfile
        navigationMenu = binding.navigationMenu

        btnProfile.setOnClickListener {
            // Toggle the visibility of the navigation menu
            if (drawerLayout.isDrawerOpen(navigationMenu)) {
                drawerLayout.closeDrawer(navigationMenu)
            } else {
                drawerLayout.openDrawer(navigationMenu)
            }
        }

        return binding.root
    }

    private fun upcomingMovies() {
        val recyclerView = binding.upcomingRecyclerview
        recyclerView.setBackgroundColor(Color.TRANSPARENT)
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
        val responseLiveData: LiveData<Response<UpComing>> = liveData {
            val response =
                retUpComing.getUpcomingMovies("Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4OTdkNGZlOTFlZGViZmJlODNiMzAzYjdkZTA3ODRiOSIsInN1YiI6IjY0YjNjOTlkMjNkMjc4MDBjOTNjNDdlYiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.t4r-V0rOgS0n2DhOMd-Gd8E61jefu_fC-0FAjozOvaw")
            emit(response)
        }
        responseLiveData.observe(this, Observer {
            val upcomingMoviesList = it.body()?.results?.toImmutableList()
            if (upcomingMoviesList != null) {
                recyclerView.adapter =
                    UpcomingRecyclerViewAdaptor(upcomingMoviesList, context!!)
            }
        })
    }

    private fun nowplayingMovies() {
        val recyclerView = binding.nowplayingRecyclerview
        recyclerView.setBackgroundColor(Color.TRANSPARENT)
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
        val responseLiveData: LiveData<Response<NowPlaying>> = liveData {
            val response =
                retNowPlaying.getNowPlayingMovies("Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4OTdkNGZlOTFlZGViZmJlODNiMzAzYjdkZTA3ODRiOSIsInN1YiI6IjY0YjNjOTlkMjNkMjc4MDBjOTNjNDdlYiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.t4r-V0rOgS0n2DhOMd-Gd8E61jefu_fC-0FAjozOvaw")
            emit(response)
        }
        responseLiveData.observe(this, Observer {
            val nowplayingMoviesList = it.body()?.results?.toImmutableList()
            if (nowplayingMoviesList != null) {
                recyclerView.adapter =
                    NowPlayingRecyclerViewAdaptor(nowplayingMoviesList, context!!)
            }
        })
    }

    private fun popularMovies() {
        val recyclerView = binding.popularRecyclerview
        recyclerView.setBackgroundColor(Color.TRANSPARENT)
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
        val responseLiveData: LiveData<Response<Popular>> = liveData {
            val response =
                retPopular.getPopularMovies("Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4OTdkNGZlOTFlZGViZmJlODNiMzAzYjdkZTA3ODRiOSIsInN1YiI6IjY0YjNjOTlkMjNkMjc4MDBjOTNjNDdlYiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.t4r-V0rOgS0n2DhOMd-Gd8E61jefu_fC-0FAjozOvaw")
            emit(response)
        }
        responseLiveData.observe(this, Observer {
            val popularList = it.body()?.results?.toImmutableList()
            if (popularList!= null) {
                recyclerView.adapter =
                    PopularRecyclerViewAdaptor(popularList, context!!)
            }
        })
    }

    private fun topratedMovies() {
        val recyclerView = binding.topratedRecyclerview
        recyclerView.setBackgroundColor(Color.TRANSPARENT)
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
        val responseLiveData: LiveData<Response<TopRated>> = liveData {
            val response =
                retTopRated.getTopRatedMovies("Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4OTdkNGZlOTFlZGViZmJlODNiMzAzYjdkZTA3ODRiOSIsInN1YiI6IjY0YjNjOTlkMjNkMjc4MDBjOTNjNDdlYiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.t4r-V0rOgS0n2DhOMd-Gd8E61jefu_fC-0FAjozOvaw")
            emit(response)
        }
        responseLiveData.observe(this, Observer {
            val popularList = it.body()?.results?.toImmutableList()
            if (popularList!= null) {
                recyclerView.adapter =
                    TopRatedRecyclerViewAdaptor(popularList, context!!)
            }
        })
    }

}