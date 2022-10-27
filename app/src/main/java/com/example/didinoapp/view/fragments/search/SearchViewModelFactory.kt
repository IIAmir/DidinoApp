package com.example.didinoapp.view.fragments.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.didinoapp.data.api.ApiService
import com.example.didinoapp.data.repository.SearchRepository

class SearchViewModelFactory(private val apiService: ApiService):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)){
            return SearchViewModel(SearchRepository(apiService)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}