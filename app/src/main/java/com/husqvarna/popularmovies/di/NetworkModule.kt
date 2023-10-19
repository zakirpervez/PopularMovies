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

/**
 * Network module for providing the retrofit instances on app level injection.
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    /**
     * Provide the api request interceptor. This interceptor is used logging the request and response of the app.
     * @return [HttpLoggingInterceptor]
     */
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor { message ->
            Timber.d(message)
        }.apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }

    /**
     * Provide okhttp client instance. This client is used to add the interceptors to the request.
     * @param httpLoggingInterceptor: [HttpLoggingInterceptor].
     * @param apiRequestInterceptor: [ApiRequestInterceptor].
     * @return [OkHttpClient]
     */
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        apiRequestInterceptor: ApiRequestInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(apiRequestInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    /**
     * Provide retrofit instance. This retrofit instance is used to make the api calls.
     * @param okHttpClient: [OkHttpClient].
     * return [Retrofit]
     */
    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /**
     * Provide the api service. This service is used to make the api calls.
     * @param retrofit: [Retrofit].
     * return [ApiService]
     */
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}
