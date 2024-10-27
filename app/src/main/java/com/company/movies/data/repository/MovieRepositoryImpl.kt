package com.company.movies.data.repository


import com.company.movies.data.data_source.RetrofitService
import com.company.movies.domain.DataState
import com.company.movies.domain.model.GenresResponse
import com.company.movies.domain.model.MovieDetailsResponse
import com.company.movies.domain.model.MoviesResponse
import com.company.movies.domain.repository.MovieRepository
import com.company.movies.util.handleApi
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
    private val retrofitService: RetrofitService
) : MovieRepository {
    override suspend fun getGenres(language: String): Flow<DataState<GenresResponse>> =
        handleApi { retrofitService.getGenres(language) }


    override suspend fun getMovieDetails(
        movieId: Int,
        language: String
    ): Flow<DataState<MovieDetailsResponse>> =
        handleApi { retrofitService.getMovieDetails (movieId, language) }

    override suspend fun discoverMovies(
        includeAdult: Boolean,
        includeVideo: Boolean,
        language: String,
        page: Int,
        sortBy: String
    ): Flow<DataState<MoviesResponse>> =
        handleApi { retrofitService.discoverMovies(includeAdult, includeVideo, language, page, sortBy) }

    override suspend fun searchMovies(
        query: String,
        includeAdult: Boolean,
        language: String,
        page: Int
    ): Flow<DataState<MoviesResponse>> =
        handleApi { retrofitService.searchMovies(query, includeAdult, language, page) }


}