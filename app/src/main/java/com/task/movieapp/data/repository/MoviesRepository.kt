package com.task.movieapp.data.repository

import com.task.movieapp.data.model.MovieInfoEntity

interface MoviesRepository {

    suspend fun getMovies(isForceUpdateData: Boolean = false): List<MovieInfoEntity>
}