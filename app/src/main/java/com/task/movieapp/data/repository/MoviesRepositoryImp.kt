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

        if (cachedList.isNotEmpty() && !isForceUpdateData && !isCacheExpired())
            return cachedList

        val currentTime = System.currentTimeMillis()
        val response = remoteDataSource.getMoviesFromServer(isForceUpdateData)
        val listToSaved = response.results.map {
            it.asEntity().copy(timestamp = currentTime)
        }
        localDataSource.clearCache()
        localDataSource.cacheList(listToSaved)

        return listToSaved
    }

    private suspend fun isCacheExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastCacheTime = localDataSource.getLastCacheTime() ?: return true
        // Check if the difference between current time and last cache time is more than 4 hours
        return currentTime - lastCacheTime > CACHE_EXPIRATION_TIME
    }


    companion object {
        private const val CACHE_EXPIRATION_TIME = 4 * 60 * 60 * 1000 // 4 hours in milliseconds
    }
}