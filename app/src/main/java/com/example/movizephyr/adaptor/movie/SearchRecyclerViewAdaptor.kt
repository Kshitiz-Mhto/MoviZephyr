package com.example.movizephyr.adaptor.movie

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movizephyr.R
import com.example.movizephyr.modal.movies.search.byname.Result

class SearchRecyclerViewAdaptor(val searchMoviesList: List<Result>?, val context: Context) : RecyclerView.Adapter<MyViewHolderSearch>() {

    private  lateinit var sp: SharedPreferences

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderSearch {
        val layoutInflator = LayoutInflater.from(parent.context)
        val listItem = layoutInflator.inflate(R.layout.search_movie_cardview, parent, false)
        sp = parent.context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return MyViewHolderSearch(listItem)
    }

    override fun getItemCount(): Int {
        return searchMoviesList!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolderSearch, position: Int) {
        var index_element = searchMoviesList!![position]
        Log.i("pp", index_element.poster_path.toString())
        Glide.with(holder.view.context)
            .load("https://image.tmdb.org/t/p/w500"+index_element.poster_path)
            .into(holder.myImageView)

        holder.myImageView.setOnClickListener{
            val editor = sp.edit()
            editor.putString("movie_name", index_element.original_title)
            editor.putString("rating", index_element.vote_average.toString())
            editor.putString("rating_num", index_element.vote_count.toString())
            editor.putString("movie_url", "https://image.tmdb.org/t/p/w500"+index_element.poster_path)
            editor.putString("description_overview", index_element.overview)
            editor.putString("movie_id", index_element.id.toString())
            editor.apply()
//            Log.i("tt", index_element.toString())
//            editor.putStringSet("genres_list", index_element.genre_ids)
            it.findNavController().navigate(
                R.id.action_homeFragment_to_infoFragment
            )
        }
    }
}

class MyViewHolderSearch(val view: View): RecyclerView.ViewHolder(view){
    val myImageView = view.findViewById<ImageView>(R.id.tvSearchMovieImage)
}