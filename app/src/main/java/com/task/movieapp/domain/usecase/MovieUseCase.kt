package com.task.movieapp.domain.usecase

import com.task.movieapp.data.model.RequestState
import com.task.movieapp.data.repository.MoviesRepository
import com.task.movieapp.utils.NetworkConnectivityException
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {
    suspend operator fun invoke(isForceUpdate: Boolean = false) = flow {
        try {
            emit(RequestState.Loading())
            emit(RequestState.Success(moviesRepository.getMovies(isForceUpdateData = isForceUpdate)))
        } catch (ex: NetworkConnectivityException) {
            emit(RequestState.Error(Throwable(message = ex.errorMessage, cause = null)))
        } catch (exception: Exception) {
            emit(RequestState.Error(exception))
        }
    }
}