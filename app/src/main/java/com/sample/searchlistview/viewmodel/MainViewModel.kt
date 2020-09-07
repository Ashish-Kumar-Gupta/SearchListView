package com.sample.searchlistview.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.searchlistview.R
import com.sample.searchlistview.model.Album
import com.sample.searchlistview.model.Albums
import com.sample.searchlistview.model.ResponseStatus
import com.sample.searchlistview.network.UseCase
import com.sample.searchlistview.view.adapter.AlbumsRecyclerViewAdapter
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

class MainViewModel(private val useCase: UseCase): ViewModel(), KoinComponent {
    var loadingLiveData: MutableLiveData<Boolean>? = null
    var albumsLiveData: MutableLiveData<ResponseStatus<Albums>>? = null
    private var albumsRecyclerViewAdapter: AlbumsRecyclerViewAdapter? = null

    init {
        loadingLiveData = MutableLiveData()
        albumsLiveData = MutableLiveData()
        albumsRecyclerViewAdapter = AlbumsRecyclerViewAdapter(R.layout.search_recycler_view_row, this)
        getAlbums()
    }

    fun getAlbums() {
        viewModelScope.launch {
            albumsLiveData?.value = ResponseStatus.loading()
            isRefreshing(true)
            try {
                val result = useCase.getAlbums()
                albumsLiveData?.postValue(ResponseStatus.success(result))
                isRefreshing(false)
            } catch (e: Exception) {
                isRefreshing(false)
                albumsLiveData?.value = ResponseStatus.error(e)
            }
        }
    }

    fun getAlbumsAdapter() = albumsRecyclerViewAdapter!!

    @RequiresApi(Build.VERSION_CODES.O)
    fun setFactsAdapter(albumList: List<Album>) {
        this.albumsRecyclerViewAdapter?.setAlbums(albumList)
        this.albumsRecyclerViewAdapter?.notifyDataSetChanged()
    }

    fun getAlbumAtPosition(position: Int): Album? {
        return albumsLiveData?.value?.response?.results?.get(position)
    }

    fun isRefreshing(isRefreshing: Boolean) {
        loadingLiveData?.postValue(isRefreshing)
    }
}