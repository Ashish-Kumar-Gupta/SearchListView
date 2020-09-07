package com.sample.searchlistview.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.sample.searchlistview.BR
import com.sample.searchlistview.model.Album
import com.sample.searchlistview.viewmodel.MainViewModel

class AlbumsRecyclerViewAdapter(@LayoutRes private val layoutId: Int, private val mainViewModel: MainViewModel): RecyclerView.Adapter<AlbumsRecyclerViewAdapter.FactViewHolder>() {
    private var albumList: List<Album>? = null

    class FactViewHolder(viewDataBinding: ViewDataBinding): RecyclerView.ViewHolder(viewDataBinding.root) {
        private val viewDataBinding = viewDataBinding

        fun bind(mainViewModel: MainViewModel, position: Int) {
            viewDataBinding.setVariable(BR.viewModel, mainViewModel)
            viewDataBinding.setVariable(BR.position, position)
            viewDataBinding.executePendingBindings()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItemViewTypeForPosition()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
        return FactViewHolder(DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), viewType, parent, false))
    }

    override fun getItemCount(): Int {
        if (albumList != null) {
            return albumList?.size!!
        }
        return 0
    }

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        holder.bind(mainViewModel, position)
    }

    fun setFacts(albumList: List<Album>) {
        this.albumList = albumList
    }

    private fun getItemViewTypeForPosition(): Int {
        return layoutId
    }
}