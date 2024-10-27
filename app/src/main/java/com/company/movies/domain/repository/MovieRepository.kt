package com.company.movies.domain.repository

import com.company.movies.domain.DataState
import com.company.movies.domain.model.GenresResponse
import com.company.movies.domain.model.MovieDetailsResponse
import com.company.movies.domain.model.MoviesResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getGenres(language: String = "en"): Flow<DataState<GenresResponse>>
    suspend fun getMovieDetails(
        movieId: Int,
        language: String = "en-US"
    ): Flow<DataState<MovieDetailsResponse>>

    suspend fun discoverMovies(
        includeAdult: Boolean = false,
        includeVideo: Boolean = false,
        language: String = "en-US",
        page: Int = 1,
        sortBy: String = "popularity.desc"
    ): Flow<DataState<MoviesResponse>>

    suspend fun searchMovies(
        query: String,
        includeAdult: Boolean = false,
        language: String = "en-US",
        page: Int = 1
    ): Flow<DataState<MoviesResponse>>
}
