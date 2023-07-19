package com.example.movizephyr.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movizephyr.R
import com.example.movizephyr.modal.movies.upcoming.Result

class UpcomingRecyclerViewAdaptor(val upcomingMoviesList: List<Result>?, val context: Context) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflator = LayoutInflater.from(parent.context)
        val listItem = layoutInflator.inflate(R.layout.upcoming_cardview, parent, false)
        return MyViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return upcomingMoviesList!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var index_element = upcomingMoviesList!![position]
        Glide.with(holder.view.context)
            .load("https://image.tmdb.org/t/p/w500"+index_element.poster_path)
            .into(holder.myImageView)
//        holder.myTextView.text = index_element.original_title
    }
}

class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){
//    val myTextView = view.findViewById<TextView>(R.id.etMovieName)
    val myImageView = view.findViewById<ImageView>(R.id.tvUpcomingImage)
}