package com.example.movizephyr.adaptor.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movizephyr.R
import com.example.movizephyr.modal.movies.watchproviders.Result

class WatchProviderRegionsRecyclerViewAdaptor (private val moviesWatchProviderList: List<Result>, val context: Context): RecyclerView.Adapter<MyViewHolderInfo>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderInfo {
        val layoutInflator = LayoutInflater.from(parent.context)
        val listItem = layoutInflator.inflate(R.layout.genre_layout, parent, false)
        return MyViewHolderInfo(listItem)
    }

    override fun onBindViewHolder(holder: MyViewHolderInfo, position: Int) {
        if (position < 10 && moviesWatchProviderList != null) {
            val indexElement = moviesWatchProviderList[position]
            holder.myTextView.text = indexElement.iso_3166_1
        }
    }

    override fun getItemCount(): Int {
        return moviesWatchProviderList?.take(10)?.size ?: 0
    }

}
