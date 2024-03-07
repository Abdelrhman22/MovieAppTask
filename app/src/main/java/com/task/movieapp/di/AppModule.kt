package com.task.movieapp.di

import com.task.movieapp.data.db.LocalDataSource
import com.task.movieapp.data.db.LocalDataSourceImp
import com.task.movieapp.data.db.MovieDao
import com.task.movieapp.data.network.MovieService
import com.task.movieapp.data.network.RemoteDataSource
import com.task.movieapp.data.network.RemoteDataSourceImp
import com.task.movieapp.data.repository.MoviesRepository
import com.task.movieapp.data.repository.MoviesRepositoryImp
import com.task.movieapp.domain.usecase.MovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(service: MovieService): RemoteDataSource =
        RemoteDataSourceImp(service)


    @Provides
    @Singleton
    fun provideLocalDataSource(dao: MovieDao): LocalDataSource =
        LocalDataSourceImp(dao)


    @Provides
    @Singleton
    fun provideRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): MoviesRepository = MoviesRepositoryImp(localDataSource, remoteDataSource)


    @Provides
    @Singleton
    fun provideMovieUseCase(repository: MoviesRepository) = MovieUseCase(repository)

}