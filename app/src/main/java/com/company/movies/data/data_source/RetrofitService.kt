package com.company.movies.data.data_source

import com.company.movies.domain.model.GenresResponse
import com.company.movies.domain.model.MovieDetailsResponse
import com.company.movies.domain.model.MoviesResponse
import com.company.movies.util.Constants.GENRES_ENDPOINT
import com.company.movies.util.Constants.MOVIE_DETAILS_ENDPOINT
import com.company.movies.util.Constants.POPULAR_ENDPOINT
import com.company.movies.util.Constants.SEARCH_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RetrofitService {

    @GET(GENRES_ENDPOINT)
    suspend fun getGenres(@Query("language") language: String = "en"): Response<GenresResponse>

    @GET(MOVIE_DETAILS_ENDPOINT)
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "en-US"
    ): Response<MovieDetailsResponse>

    @GET(POPULAR_ENDPOINT)
    suspend fun discoverMovies(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): Response<MoviesResponse>

    @GET(SEARCH_ENDPOINT)
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<MoviesResponse>

}