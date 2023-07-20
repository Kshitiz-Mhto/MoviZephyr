package com.example.movizephyr.endpoints.movies.search

import com.example.movizephyr.modal.movies.search.byname.SearchMovieName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SearchByMovies {
    @GET("/3/search/movie")
    suspend fun getSearchByMovieName(@Header("Authorization") auth:String,@Query("query") movie_name:String, @Query("api_key") api_key:String): Response<SearchMovieName>
}