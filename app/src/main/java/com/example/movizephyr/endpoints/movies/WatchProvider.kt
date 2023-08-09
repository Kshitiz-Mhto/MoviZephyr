package com.example.movizephyr.endpoints.movies

import com.example.movizephyr.modal.movies.watchproviders.providers.WatchProviders
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface WatchProvider {

    @GET("/3/watch/providers/movie")
    suspend fun getWatchProviders(@Header("Authorization") authorization: String): Response<WatchProviders>

    @GET("/3/tv/{series_id}/watch/providers")
    suspend fun getWatchProvidersByMovieId(@Header("Authorization") authorization: String, @Path("series_id") id : String): Response<WatchProviders>

}