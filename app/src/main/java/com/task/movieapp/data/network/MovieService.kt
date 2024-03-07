package com.task.movieapp.data.network

import com.task.movieapp.data.model.MoviesResponse
import com.task.movieapp.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("trending/movie/day?api_key=${Constants.API_KEY}")
    suspend fun getMoviesFromServer(/*@Query("api_key") apiKey: String?*/): MoviesResponse

}