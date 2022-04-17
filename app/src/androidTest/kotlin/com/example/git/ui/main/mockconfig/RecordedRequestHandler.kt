package com.example.git.ui.main.mockconfig

import okhttp3.Headers
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

import java.util.Scanner

abstract class RecordedRequestHandler {

    abstract fun canHandleRequest(request: RecordedRequest): Boolean

    abstract fun getResponse(request: RecordedRequest): MockResponse

    fun getResponseWithBody(code: Int, body: String): MockResponse {
        val mockResponse = MockResponse().setResponseCode(code)

        if (body.isNotEmpty()) {
            mockResponse.setBody(body)
        }

        return mockResponse
    }

    fun getResponseWithoutBody(code: Int): MockResponse {
        return MockResponse().setResponseCode(code)
    }

    fun getResponseWithBodyAndHeaders(
        code: Int, body: String,
        headersMap: Map<String, String>
    ): MockResponse {
        val mockResponse = getResponseWithBody(code, body)
        if (headersMap.isNotEmpty()) {
            val headers = Headers.of(headersMap)
            mockResponse.headers = headers
        }
        return mockResponse
    }

    fun readJsonFile(filename: String): String {
        if (filename.isEmpty()) {
            return ""
        }

        val inputStream = this.javaClass.classLoader?.getResourceAsStream(filename)
        val s = Scanner(inputStream).useDelimiter("\\A")
        return if (s.hasNext()) s.next() else ""
    }

    fun getRequestBody(request: RecordedRequest): String {
        return request.body.readUtf8()
    }
}
