package com.sample.searchlistview.view

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.searchlistview.R
import com.sample.searchlistview.databinding.ActivityMainBinding
import com.sample.searchlistview.model.Album
import com.sample.searchlistview.model.Albums
import com.sample.searchlistview.model.ResponseStatus
import com.sample.searchlistview.network.UseCase
import com.sample.searchlistview.utils.RESPONSE
import com.sample.searchlistview.viewmodel.MainViewModel
import com.sample.searchlistview.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private val useCase: UseCase by inject()
    private val mainViewModelFactory : MainViewModelFactory = MainViewModelFactory(useCase)
    private val mainViewModel : MainViewModel by lazy {
        ViewModelProviders.of(this, mainViewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        linearLayoutManager = LinearLayoutManager(this)
        binding?.searchRecyclerView.apply {
            this?.layoutManager = linearLayoutManager
            this?.itemAnimator = DefaultItemAnimator()
        }

        binding?.viewModel = mainViewModel
        mainViewModel.loadingLiveData?.observe(this, progressObserver)
        mainViewModel.albumsLiveData?.observe(this, responseObserver)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu?.findItem(R.id.search)?.actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        return true
    }

    private val progressObserver = Observer<Boolean> { isRefreshing ->
        swipeRefreshLayout.isRefreshing = isRefreshing
    }

    private val responseObserver = Observer<ResponseStatus<Albums>> { result ->
        when (result?.responseType) {
            RESPONSE.LOADING -> {
            }
            RESPONSE.ERROR -> {
                if (searchRecyclerView.adapter?.itemCount == 0) {
                    errorLayout.visibility = View.VISIBLE
                }
            }
            RESPONSE.SUCCESS -> {
                if (errorLayout.visibility == View.VISIBLE) {
                    errorLayout.visibility = View.GONE
                }
                val albums = result.response as Albums
                val  albumList =  albums.results as ArrayList<Album>
                mainViewModel.setFactsAdapter(albumList)
            }
        }
    }
}