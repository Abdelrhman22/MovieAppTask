package com.task.movieapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.movieapp.data.model.MovieInfoEntity
import com.task.movieapp.data.model.RequestState
import com.task.movieapp.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {

    private val _moviesList = MutableStateFlow<RequestState<List<MovieInfoEntity>>>(RequestState.Loading())
    val moviesList: StateFlow<RequestState<List<MovieInfoEntity>>> = _moviesList



    fun getMoviesList() = viewModelScope.launch {
        movieUseCase.invoke().collect {
            _moviesList.value = it
        }
    }
}