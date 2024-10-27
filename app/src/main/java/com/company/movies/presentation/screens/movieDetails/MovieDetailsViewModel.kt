package com.company.movies.presentation.screens.movieDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.movies.domain.DataState
import com.company.movies.domain.model.MovieDetailsResponse
import com.company.movies.domain.model.MoviesResponse
import com.company.movies.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _movieDetailsState =
        MutableStateFlow<MovieDetailsUiState>(MovieDetailsUiState.Loading)
    val movieDetailsState: StateFlow<MovieDetailsUiState> get() = _movieDetailsState

    private val _mostSearchedState =
        MutableStateFlow<MostSearchedUiState>(MostSearchedUiState.Loading)
    val mostSearchedState: StateFlow<MostSearchedUiState> get() = _mostSearchedState

    fun fetchMovieDetails(movieId: Int, language: String) {
        viewModelScope.launch {
            movieRepository.getMovieDetails(movieId, language).collect { dataState ->
                when (dataState) {
                    is DataState.Success -> {
                        _movieDetailsState.value = MovieDetailsUiState.Success(dataState.data)
                    }

                    is DataState.Error -> {
                        _movieDetailsState.value = MovieDetailsUiState.Error(dataState.message)
                    }

                    DataState.Loading -> {
                        _movieDetailsState.value = MovieDetailsUiState.Loading
                    }

                    DataState.Empty -> {
                        _movieDetailsState.value = MovieDetailsUiState.Empty
                    }
                }
            }
        }
    }

    fun fetchMostSearchedMovies(language: String, page: Int = 1) {
        viewModelScope.launch {
            movieRepository.discoverMovies(
                includeAdult = false,
                includeVideo = false,
                language = language,
                page = page,
                sortBy = "popularity.desc"
            ).collect { dataState ->
                when (dataState) {
                    is DataState.Success -> {
                        _mostSearchedState.value =
                            MostSearchedUiState.Success(dataState.data.results) // Assuming results is a List<MoviesResponse.Result>
                    }

                    is DataState.Error -> {
                        _mostSearchedState.value = MostSearchedUiState.Error(dataState.message)
                    }

                    DataState.Loading -> {
                        _mostSearchedState.value = MostSearchedUiState.Loading
                    }

                    DataState.Empty -> {
                        _mostSearchedState.value = MostSearchedUiState.Empty
                    }
                }
            }
        }
    }
}

sealed class MostSearchedUiState {
    data object Loading : MostSearchedUiState()
    data class Success(val movies: List<MoviesResponse.Result>) : MostSearchedUiState()
    data class Error(val message: String) : MostSearchedUiState()
    data object Empty : MostSearchedUiState()
}

sealed class MovieDetailsUiState {
    data object Loading : MovieDetailsUiState()
    data class Success(val movieDetails: MovieDetailsResponse) : MovieDetailsUiState()
    data class Error(val message: String) : MovieDetailsUiState()
    data object Empty : MovieDetailsUiState()
}
