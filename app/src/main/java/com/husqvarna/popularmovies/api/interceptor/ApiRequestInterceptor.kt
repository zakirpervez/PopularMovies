package com.husqvarna.popularmovies.api.interceptor

import com.husqvarna.popularmovies.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Interceptor to add headers to the api request.
 */
class ApiRequestInterceptor @Inject constructor() : Interceptor {
    /**
     * Intercepts the request and adds the headers.
     * @param chain The interceptor chain.
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.header(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE)
        requestBuilder.header(AUTHORIZATION_KEY, AUTHORIZATION_VALUE)
        return chain.proceed(requestBuilder.build())
    }

    companion object {
        private const val CONTENT_TYPE_KEY = "Content-Type"
        private const val AUTHORIZATION_KEY = "Authorization"

        private const val CONTENT_TYPE_VALUE = "application/json"
        private const val AUTHORIZATION_VALUE = "Bearer ${BuildConfig.ACCESS_TOKEN}"
    }
}
