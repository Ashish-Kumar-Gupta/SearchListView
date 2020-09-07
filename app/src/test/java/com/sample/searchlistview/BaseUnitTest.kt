package com.sample.searchlistview

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import java.io.File

abstract class BaseUnitTest: KoinTest {
    private lateinit var mockWebServer: MockWebServer
    private var shouldStart = false

    @Before
    open fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        startMockServer(true)
    }

    fun mockNetworkResponseWithFileContent(fileName: String, responseCode: Int) = mockWebServer.enqueue(
        MockResponse()
            .setResponseCode(responseCode)
            .setBody(getDummyResponse(fileName)
        )
    )

    fun getDummyResponse(path : String) : String {
        val uri = javaClass.classLoader!!.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }

    private fun startMockServer(shouldStart: Boolean){
        if (shouldStart){
            this.shouldStart = shouldStart
            mockWebServer = MockWebServer()
            mockWebServer.start()
        }
    }

    fun getMockWebServerUrl() = mockWebServer.url("/").toString()

    private fun stopMockServer() {
        if (shouldStart) {
            mockWebServer.shutdown()
        }
    }

    @After
    open fun tearDown() {
        Dispatchers.resetMain()
        stopMockServer()
        stopKoin()
    }
}