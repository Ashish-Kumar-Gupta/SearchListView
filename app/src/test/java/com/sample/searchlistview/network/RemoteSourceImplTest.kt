package com.sample.searchlistview.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sample.searchlistview.BaseUnitTest
import com.sample.searchlistview.di.configureTestAppComponent
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class RemoteSourceImplTest: BaseUnitTest() {
    private lateinit var remoteSourceImpl: RemoteSourceImpl

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun start(){
        super.setUp()
        startKoin {
            modules(configureTestAppComponent(getMockWebServerUrl()))
        }
    }

    @Test
    fun testRemoteSourceDataPopulationAsExpected() =  runBlocking {

        mockNetworkResponseWithFileContent("success_response.json", HttpURLConnection.HTTP_OK)
        remoteSourceImpl = RemoteSourceImpl()
        val dummyResponse = remoteSourceImpl.getAlbums()

        Assert.assertNotNull(dummyResponse)
        Assert.assertEquals(dummyResponse.resultCount, 50)
    }
}