package com.example.movizephyr

import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.movizephyr.databinding.FragmentLandingBinding

class LandingFragment : Fragment() {

    private lateinit var binding: FragmentLandingBinding
    private var delayedNavigation: Runnable? = null
    private val handler: Handler by lazy { Handler() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLandingBinding.inflate(inflater, container, false)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            // For API 28 and above, use postDelayed directly on the View
            delayedNavigation = Runnable {
                navigateToHomeFragment()
            }
            binding.root.postDelayed(delayedNavigation!!, 3000)
        } else {
            // For API versions less than 28, use the Handler
            delayedNavigation = Runnable {
                navigateToHomeFragment()
            }
            handler.postDelayed(delayedNavigation!!, 3000)
        }

        binding.btnToHome.setOnClickListener {
            navigateToHomeFragment()
        }

        return binding.root
    }

    private fun navigateToHomeFragment() {
        findNavController().navigate(
            R.id.action_landingFragment_to_homeFragment
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        delayedNavigation?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                binding.root.removeCallbacks(it)
            } else {
                handler.removeCallbacks(it)
            }
        }
    }

}


//  just for knowledge

//    // for fullscreen
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
////        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS) // transparent
//    }
//
//
//    // remove full screen during transition between other fragment or activity
//    override fun onDetach() {
//        super.onDetach()
//        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
//    }