package com.example.movizephyr.endpoints.movies

import com.example.movizephyr.modal.movies.watchproviders.WatchProvidersRegionsList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface WatchProvidersRegions {

    @GET("/3/watch/providers/regions")
    suspend fun getWatchProviderRegion(@Header("Authorization") authorization: String):Response<WatchProvidersRegionsList>

}