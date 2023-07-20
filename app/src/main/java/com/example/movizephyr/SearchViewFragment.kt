package com.example.movizephyr

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.movizephyr.databinding.FragmentSearchViewBinding
import com.example.movizephyr.endpoints.movies.search.SearchByMovies
import com.example.movizephyr.modal.RetrofitInstance

class SearchViewFragment : Fragment() {

    private lateinit var binding: FragmentSearchViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchViewBinding.inflate(inflater,container, false)

        return binding.root
    }

}