package com.task.movieapp.data.network

import com.task.movieapp.data.model.MoviesResponse

interface RemoteDataSource {

    suspend fun getMoviesFromServer(isForceUpdate: Boolean = false): MoviesResponse

}