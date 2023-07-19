package com.example.movizephyr.endpoints.movies

import com.example.movizephyr.modal.movies.nowplaying.NowPlaying
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface NowPlayingMovies {

    @GET("/3/movie/now_playing")
    suspend fun getNowPlayingMovies(@Header("Authorization") authorization: String): Response<NowPlaying>
}