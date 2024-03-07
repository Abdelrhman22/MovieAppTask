package com.task.movieapp.data.db

import com.task.movieapp.data.model.MovieInfoEntity
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor(private val movieDao: MovieDao) :
    LocalDataSource {
    override suspend fun getListFromCache(): List<MovieInfoEntity> = movieDao.getListFromCache()

    override suspend fun clearCache() {
        movieDao.clearCache()
    }

    override suspend fun cacheList(list: List<MovieInfoEntity>) {
        movieDao.cacheList(*list.toTypedArray())
    }


}