package com.husqvarna.popularmovies.di

import com.husqvarna.popularmovies.BuildConfig
import com.husqvarna.popularmovies.api.ApiService
import com.husqvarna.popularmovies.api.interceptor.ApiRequestInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor { message ->
            Timber.d(message)
        }.apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        apiRequestInterceptor: ApiRequestInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(apiRequestInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}
