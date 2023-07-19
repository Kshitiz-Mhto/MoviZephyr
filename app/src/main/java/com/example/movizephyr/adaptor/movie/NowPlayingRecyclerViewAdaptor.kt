package com.example.movizephyr.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movizephyr.R
import com.example.movizephyr.modal.movies.nowplaying.Result


class NowPlayingRecyclerViewAdaptor(val nowplayingMoviesList: List<Result>?, val context: Context) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflator = LayoutInflater.from(parent.context)
        val listItem = layoutInflator.inflate(R.layout.home_movies_cardview, parent, false)
        return MyViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return nowplayingMoviesList!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var index_element = nowplayingMoviesList!![position]
        Glide.with(holder.view.context)
            .load("https://image.tmdb.org/t/p/w500"+index_element.poster_path)
            .into(holder.myImageView)
    }
}
