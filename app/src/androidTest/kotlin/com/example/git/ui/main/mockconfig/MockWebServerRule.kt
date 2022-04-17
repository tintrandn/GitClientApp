package com.example.git.ui.main.mockconfig

import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.ExternalResource
import java.io.IOException

class MockWebServerRule constructor(val dispatcher: MockResponseDispatcher) : ExternalResource() {

    val mockWebServer = MockWebServer()

    @Throws(IOException::class)
    override fun before() {
        mockWebServer.dispatcher = dispatcher
        mockWebServer.start(8081)
    }

    override fun after() {
        try {
            mockWebServer.shutdown()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}

