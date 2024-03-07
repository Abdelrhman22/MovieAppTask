package com.task.movieapp.data.network

import com.task.movieapp.data.model.MoviesResponse
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(private val movieService: MovieService) :
    RemoteDataSource {

    override suspend fun getMoviesFromServer(isForceUpdate: Boolean): MoviesResponse {
        return movieService.getMoviesFromServer()
    }

}