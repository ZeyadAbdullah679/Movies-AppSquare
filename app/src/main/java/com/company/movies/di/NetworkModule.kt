package com.company.movies.di


import com.company.movies.BuildConfig
import com.company.movies.data.data_source.RetrofitService
import com.company.movies.data.repository.MovieRepositoryImpl
import com.company.movies.domain.repository.MovieRepository
import com.company.movies.util.Constants.BASE_ENDPOINT
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(loggingInterceptor)
            addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", BuildConfig.BEARER_TOKEN)
                    .build()
                chain.proceed(request)
            }
            connectTimeout(20, TimeUnit.SECONDS) // connect timeout
            readTimeout(30, TimeUnit.SECONDS) // socket timeout
            writeTimeout(20, TimeUnit.SECONDS) // write timeout
        }.build()
    }

    @Provides
    @Singleton
    fun provideService(okHttpClient: OkHttpClient): RetrofitService {
        return Retrofit.Builder()
            .baseUrl(BASE_ENDPOINT)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(RetrofitService::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: RetrofitService): MovieRepository {
        return MovieRepositoryImpl(api)
    }

}