package com.example.movizephyr.endpoints.movies

import com.example.movizephyr.modal.movies.watchprovider.Buy
import com.example.movizephyr.modal.movies.watchprovider.Rent
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface WatchProviders {

    @GET("/3/movie/{movie_id}/watch/providers")
    suspend fun getWatchProvidersForBuying(@Header("Authorization") authorization: String, @Path("movie_id") id:String):Response<List<Buy>>

    @GET("/3/movie/{movie_id}/watch/providers")
    suspend fun getWatchProvidersForRent(@Header("Authorization") authorization: String, @Path("movie_id") id:String):Response<List<Rent>>
}