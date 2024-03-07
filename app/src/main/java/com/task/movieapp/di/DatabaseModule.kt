package com.task.movieapp.di

import android.content.Context
import com.task.movieapp.data.db.MovieDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun injectRoomDatabase(@ApplicationContext context: Context) =
        MovieDataBase.getInstance(context)

    @Singleton
    @Provides
    fun injectDao(database: MovieDataBase) = database.getDao()
}