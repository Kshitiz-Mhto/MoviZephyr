package com.example.movizephyr.adaptor.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movizephyr.R
import com.example.movizephyr.modal.movies.watchproviders.providers.Result

class WatchProvidersRecyclerviewAdaptor(private val moviesWatchProviderList: List<Result>, val context: Context): RecyclerView.Adapter<MyViewHolderProviders>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderProviders {
        val layoutInflator = LayoutInflater.from(parent.context)
        val listItem = layoutInflator.inflate(R.layout.watch_provider_cardview, parent, false)
        return MyViewHolderProviders(listItem)
    }

    override fun onBindViewHolder(holder: MyViewHolderProviders, position: Int) {
//        if (position < 10 && moviesWatchProviderList != null) {
            val indexElement = moviesWatchProviderList[position]
            Glide.with(holder.view.context)
                .load("https://image.tmdb.org/t/p/w500"+indexElement.logo_path)
                .into(holder.tvProvider)
//        }
    }

    override fun getItemCount(): Int {
        return moviesWatchProviderList.size
    }

}

class MyViewHolderProviders(val view: View):RecyclerView.ViewHolder(view){
    val tvProvider = view.findViewById<ImageView>(R.id.tvProviderImage)
}