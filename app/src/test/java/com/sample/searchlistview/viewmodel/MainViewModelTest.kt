package com.sample.searchlistview.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.sample.searchlistview.BaseUnitTest
import com.sample.searchlistview.di.configureTestAppComponent
import com.sample.searchlistview.model.Albums
import com.sample.searchlistview.model.ResponseStatus
import com.sample.searchlistview.network.UseCase
import com.sample.searchlistview.utils.RESPONSE
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin

@RunWith(JUnit4::class)
class MainViewModelTest: BaseUnitTest() {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var mainViewModel: MainViewModel

    @MockK
    lateinit var useCase: UseCase

    @Before
    fun start() {
        super.setUp()
        MockKAnnotations.init(this)
        startKoin {
            modules(configureTestAppComponent(getMockWebServerUrl()))
        }
    }

    @Test
    fun testMainViewModelDataPopulationAsExpected() {
        mainViewModel = MainViewModel(useCase)
        val sampleResponse = getDummyResponse("success_response.json")
        var dummyResponse = Gson().fromJson(sampleResponse, Albums::class.java)
        coEvery {
            useCase.getAlbums()
        } returns dummyResponse
        mainViewModel.albumsLiveData?.observeForever {
        }
        mainViewModel.getAlbums()

        assert(mainViewModel.albumsLiveData?.value != null)
        assert(mainViewModel.albumsLiveData?.value!!.responseType == RESPONSE.SUCCESS)
        val testResult = mainViewModel.albumsLiveData?.value as ResponseStatus<Albums>
        Assert.assertEquals(testResult.response!!.resultCount, 50)
    }
}