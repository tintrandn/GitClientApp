package com.example.git.ui.main.mockconfig

import android.os.Handler
import androidx.test.platform.app.InstrumentationRegistry
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class MockResponseDispatcher internal constructor() : Dispatcher() {

    private val searchUsersRecordedRequestHandler = SearchUsersRecordedRequestHandler()

    override fun dispatch(request: RecordedRequest): MockResponse {

        if (searchUsersRecordedRequestHandler.canHandleRequest(request)) {
            return searchUsersRecordedRequestHandler.getResponse(request)
        }

        return throwUnsupportedException("Could not handle", request.path)
    }

    private fun throwUnsupportedException(message: String, path: String): MockResponse {
        val mainThreadHandler =
            Handler(InstrumentationRegistry.getInstrumentation().targetContext.mainLooper)
        mainThreadHandler.post { throw UnsupportedOperationException("$message $path") }

        throw UnsupportedOperationException()
    }

    companion object {

        private val ACCEPT_TYPE = "Accept"
        private val MSL_V1_JSON = "application/vnd.msl.v1+json"
        private val BAD_REQUEST = 400
    }
}
