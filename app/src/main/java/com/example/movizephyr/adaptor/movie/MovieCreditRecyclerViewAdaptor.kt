package com.example.movizephyr.adaptor.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movizephyr.R
import com.example.movizephyr.modal.movies.credits.Cast

class MovieCreditRecyclerViewAdaptor(val movieCreditList: List<Cast>?, val context: Context) : RecyclerView.Adapter<MyViewHolderCredit>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderCredit {
        val layoutInflator = LayoutInflater.from(parent.context)
        val listItem = layoutInflator.inflate(R.layout.cast_cardview, parent, false)
        return MyViewHolderCredit(listItem)
    }

    override fun getItemCount(): Int {
        return movieCreditList!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolderCredit, position: Int) {
        var index_element = movieCreditList!![position]
        Glide.with(holder.view.context)
            .load("https://image.tmdb.org/t/p/w500"+index_element.profile_path)
            .into(holder.myImageView)
        holder.myTextView.text = index_element.name
    }
}
class MyViewHolderCredit(val view: View): RecyclerView.ViewHolder(view){
    val myImageView = view.findViewById<ImageView>(R.id.tvCreditImage)
    val myTextView = view.findViewById<TextView>(R.id.creditName)
}