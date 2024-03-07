package com.task.movieapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.task.movieapp.data.model.MovieInfoEntity
import com.task.movieapp.utils.Constants

@Dao
interface MovieDao {

    @Query("SELECT * FROM ${Constants.TABLE_NAME}")
    suspend fun getListFromCache(): List<MovieInfoEntity>

    @Query("DELETE FROM ${Constants.TABLE_NAME}")
    suspend fun clearCache()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun cacheList(vararg list: MovieInfoEntity)
}