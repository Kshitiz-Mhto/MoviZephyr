package com.example.movizephyr

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movizephyr.adaptor.movie.SearchRecyclerViewAdaptor
import com.example.movizephyr.databinding.FragmentSearchViewBinding
import com.example.movizephyr.endpoints.movies.search.SearchByMovies
import com.example.movizephyr.modal.RetrofitInstance
import com.example.movizephyr.modal.movies.search.byname.SearchMovieName
import okhttp3.internal.toImmutableList
import retrofit2.Response
import java.util.Locale

class SearchViewFragment : Fragment() {

    private lateinit var binding: FragmentSearchViewBinding
    private lateinit var retSearchMovieName: SearchByMovies
    private lateinit var sp: SharedPreferences
    private lateinit var searched_movie_name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchViewBinding.inflate(inflater,container, false)
        retSearchMovieName = RetrofitInstance.getRetrofitInstance().create(SearchByMovies::class.java)
        sp = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        val recyclerView = binding.searchRecylervew
        recyclerView.setBackgroundColor(Color.TRANSPARENT)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        searched_movie_name = sp.getString("searched_movie_name", "") ?: ""
        binding.searchMeSearchFragment.setQuery(searched_movie_name, false)

        val responseLiveData: LiveData<Response<SearchMovieName>> = liveData {
            val response = retSearchMovieName.getSearchByMovieName( "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4OTdkNGZlOTFlZGViZmJlODNiMzAzYjdkZTA3ODRiOSIsInN1YiI6IjY0YjNjOTlkMjNkMjc4MDBjOTNjNDdlYiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.t4r-V0rOgS0n2DhOMd-Gd8E61jefu_fC-0FAjozOvaw",searched_movie_name,"897d4fe91edebfbe83b303b7de0784b9")
            emit(response)
        }
        responseLiveData.observe(this, Observer {
            val searchMovieList = it.body()?.results?.toImmutableList()
            if (searchMovieList != null) {
                recyclerView.adapter = SearchRecyclerViewAdaptor(searchMovieList, context!!)
            }
        })

        binding.searchMeSearchFragment.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchMeSearchFragment.clearFocus()
                val searchText = query!!.lowercase(Locale.getDefault())
                if (searchText.isNotEmpty() or searchText.isNotBlank()){
                    val editor = sp.edit()
                    editor.putString("searched_movie_name", searchText)
                    editor.apply()
                    findNavController().navigate(R.id.action_searchViewFragment_self)
                }
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        binding.btnProfile.setOnClickListener {
            findNavController().navigate(
                R.id.action_searchViewFragment_to_homeFragment
            )
        }

        return binding.root
    }

}