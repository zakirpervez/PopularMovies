package com.husqvarna.popularmovies.api.interceptor

import com.husqvarna.popularmovies.BuildConfig
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.junit.Before
import org.junit.Test


class ApiRequestInterceptorTest {
    private lateinit var interceptor: Interceptor
    private lateinit var chain: Interceptor.Chain
    private val requestBuilder = mockk<Request.Builder>()
    private val request = mockk<Request>()
    private val response = mockk<Response>()


    @Before
    fun setUp() {
        interceptor = ApiRequestInterceptor()
        chain = mockk()

        coEvery { chain.request() } returns request
        coEvery { chain.proceed(any()) } returns response
    }

    @Test
    fun `intercept adds Content-Type header`() {
        every { request.newBuilder() } returns requestBuilder
        every { requestBuilder.header(any(), any()) } returns requestBuilder
        every { requestBuilder.build() } returns request
        every { chain.request() } returns request

        interceptor.intercept(chain)

        verify(exactly = 1) {
            request.newBuilder()
            requestBuilder.header("Content-Type", "application/json")
            requestBuilder.build()
            chain.proceed(request)
        }
    }

    @Test
    fun `intercept adds Authorization header with BuildConfig ACCESS_TOKEN`() {
        every { request.newBuilder() } returns requestBuilder
        every { requestBuilder.header(any(), any()) } returns requestBuilder
        every { requestBuilder.build() } returns request
        every { chain.request() } returns request

        interceptor.intercept(chain)

        verify(exactly = 1) {
            request.newBuilder()
            requestBuilder.header("Authorization", "Bearer ${BuildConfig.ACCESS_TOKEN}")
            requestBuilder.build()
            chain.proceed(request)
        }
    }
}
