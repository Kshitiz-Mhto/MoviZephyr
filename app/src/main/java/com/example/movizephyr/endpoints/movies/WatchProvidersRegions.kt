package com.example.movizephyr.endpoints.movies

import com.example.movizephyr.modal.movies.watchproviders.WatchProviders
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface WatchProviders {

    @GET("/3/watch/providers/regions")
    suspend fun getWatchProvidersForBuying(@Header("Authorization") authorization: String):Response<WatchProviders>

}