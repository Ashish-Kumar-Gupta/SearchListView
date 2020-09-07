package com.sample.searchlistview.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.searchlistview.network.UseCase

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val useCase: UseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}