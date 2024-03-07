package com.task.movieapp.domain.usecase

import com.task.movieapp.data.model.RequestState
import com.task.movieapp.data.repository.MoviesRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {
    suspend operator fun invoke(isForceUpdate: Boolean) = flow {
        try {
            emit(RequestState.Loading())
            emit(RequestState.Success(moviesRepository.getMovies(isForceUpdateData = isForceUpdate)))
        } catch (exception: Exception) {
            emit(RequestState.Error(exception))
        }
    }
}