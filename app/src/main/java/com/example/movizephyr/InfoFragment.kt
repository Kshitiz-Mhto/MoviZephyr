package com.example.movizephyr

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movizephyr.databinding.FragmentInfoBinding
import kotlin.Exception

class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            binding = FragmentInfoBinding.inflate(inflater, container, false)

            binding.btnRating.text = "IMBD " + "7.8"

            return binding.root
    }
}