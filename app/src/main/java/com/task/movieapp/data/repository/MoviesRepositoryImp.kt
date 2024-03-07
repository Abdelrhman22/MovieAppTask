package com.task.movieapp.data.repository

import com.task.movieapp.data.db.LocalDataSource
import com.task.movieapp.data.model.MovieInfoEntity
import com.task.movieapp.data.network.RemoteDataSource
import javax.inject.Inject

class MoviesRepositoryImp @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : MoviesRepository {
    override suspend fun getMovies(isForceUpdateData: Boolean): List<MovieInfoEntity> {

        val cachedList = localDataSource.getListFromCache()

        if (cachedList.isNotEmpty() && !isForceUpdateData)
            return cachedList

        val response = remoteDataSource.getMoviesFromServer(isForceUpdateData)
        val listToSaved = response.results.map {
            it.asEntity()
        }
        localDataSource.clearCache()
        localDataSource.cacheList(listToSaved)

        return listToSaved
    }
}