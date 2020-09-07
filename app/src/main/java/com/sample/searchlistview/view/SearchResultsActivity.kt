package com.sample.searchlistview.view

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.searchlistview.R
import com.sample.searchlistview.databinding.ActivityMainBinding

class SearchResultsActivity: AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var linearLayoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        linearLayoutManager = LinearLayoutManager(this)
        binding?.searchRecyclerView.apply {
            this?.layoutManager = linearLayoutManager
            this?.itemAnimator = DefaultItemAnimator()
        }
        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            Toast.makeText(this, query, Toast.LENGTH_LONG).show()
        }
    }
}