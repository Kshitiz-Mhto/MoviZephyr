package com.example.movizephyr.adaptor.movie

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movizephyr.R
import com.example.movizephyr.modal.movies.recommendation.Result

class RecommendationRecyclerViewAdaptor(private val recommendMoviesList: List<Result>?, val context: Context) : RecyclerView.Adapter<MyViewHolderSearch>() {

    private lateinit var sp: SharedPreferences

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderSearch {
        val layoutInflator = LayoutInflater.from(parent.context)
        val listItem = layoutInflator.inflate(R.layout.search_movie_cardview, parent, false)
        sp = parent.context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return MyViewHolderSearch(listItem)
    }

    override fun getItemCount(): Int {
        return recommendMoviesList!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolderSearch, position: Int) {
        var index_element = recommendMoviesList!![position]
        Glide.with(holder.view.context)
            .load("https://image.tmdb.org/t/p/w500"+index_element.poster_path)
            .into(holder.myImageView)

        holder.myImageView.setOnClickListener{
            val editor = sp.edit()
            editor.putString("movie_id", index_element.id.toString())
            editor.apply()
            it.findNavController().navigate(
                R.id.action_infoFragment_self
            )
        }
    }
}
