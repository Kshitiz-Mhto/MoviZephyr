package com.example.movizephyr.adaptor.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.movizephyr.R
import com.example.movizephyr.modal.movies.search.byid.Genre

class SearchMovieByIdRecyclerViewAdaptor(private val moviesGenreList: List<Genre>, val context: Context): RecyclerView.Adapter<MyViewHolderInfo>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderInfo {
        val layoutInflator = LayoutInflater.from(parent.context)
        val listItem = layoutInflator.inflate(R.layout.genre_layout, parent, false)
        return MyViewHolderInfo(listItem)
    }

    override fun onBindViewHolder(holder: MyViewHolderInfo, position: Int) {
        var index_element = moviesGenreList!![position]
        holder.myTextView.text =  index_element.name
    }

    override fun getItemCount(): Int {
        return moviesGenreList!!.size
    }

}

class MyViewHolderInfo(val view: View): RecyclerView.ViewHolder(view){
    val myTextView = view.findViewById<Button>(R.id.btnInfoGenre)
}