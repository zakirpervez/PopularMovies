package com.husqvarna.popularmovies.api.base

import com.husqvarna.popularmovies.api.models.ApiResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.io.IOException


class SafeApiTest {
    private lateinit var safeApi: SafeApi

    @MockK
    private lateinit var mockResponse: Response<String>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        safeApi = SafeApi()
    }

    @Test
    fun `test successful API call`() = runBlocking {
        coEvery { mockResponse.isSuccessful } returns true
        coEvery { mockResponse.body() } returns "Response Data"

        val result = safeApi.execute { mockResponse }

        verify { mockResponse.isSuccessful }
        verify { mockResponse.body() }

        assert(result is ApiResult.Success)
        assert((result as ApiResult.Success).data == "Response Data")
    }

    @Test
    fun `test API call with null response body`() = runBlocking {
        coEvery { mockResponse.isSuccessful } returns true
        coEvery { mockResponse.body() } returns null
        coEvery { mockResponse.code() } returns 200

        val result = safeApi.execute { mockResponse }

        verify { mockResponse.isSuccessful }
        verify { mockResponse.body() }

        assert(result is ApiResult.Error)
        assert((result as ApiResult.Error).errorMessage == "$ERROR_RESPONSE_NULL 200")
    }

    @Test
    fun `test API call with non-successful status code`() = runBlocking {
        coEvery { mockResponse.isSuccessful } returns false
        coEvery { mockResponse.message() } returns "Not Found"
        coEvery { mockResponse.code() } returns 404

        val result = safeApi.execute { mockResponse }

        verify { mockResponse.isSuccessful }
        verify { mockResponse.message() }
        verify { mockResponse.code() }

        assert(result is ApiResult.Error)
        assert((result as ApiResult.Error).errorMessage == "Not Found 404")
    }

    @Test
    fun `test API call with empty error message`() = runBlocking {
        coEvery { mockResponse.isSuccessful } returns false
        coEvery { mockResponse.message() } returns ""
        coEvery { mockResponse.code() } returns 500

        val result = safeApi.execute { mockResponse }

        verify { mockResponse.isSuccessful }
        verify { mockResponse.message() }
        verify { mockResponse.code() }

        assert(result is ApiResult.Error)
        assert((result as ApiResult.Error).errorMessage == "$ERROR_UNKNOWN 500")
    }

    @Test
    fun `test exception handling`() = runBlocking {
        coEvery { mockResponse.isSuccessful } throws IOException("Network error")

        val result = safeApi.execute { mockResponse }

        verify { mockResponse.isSuccessful }

        assert(result is ApiResult.Error)
        assert((result as ApiResult.Error).errorMessage == "Network error")
    }
}
