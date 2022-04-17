package com.example.git.ui.main.mockconfig

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class SearchUsersRecordedRequestHandler : RecordedRequestHandler() {

    override fun canHandleRequest(request: RecordedRequest): Boolean {
        return request.method == "GET" && request.path.contains(SEARCH_USER)
    }

    override fun getResponse(request: RecordedRequest): MockResponse {
        val body = readJsonFile("search/get.json")
        return getResponseWithBody(200, body)
    }

    companion object {

        private const val SEARCH_USER = "/search/users"
    }
}
