package com.task.movieapp.data.db

import com.task.movieapp.data.model.MovieInfoEntity

interface LocalDataSource {

    suspend fun getListFromCache(): List<MovieInfoEntity>

    suspend fun clearCache()

    suspend fun cacheList(list: List<MovieInfoEntity>)

    suspend fun getLastCacheTime() : Long?
}