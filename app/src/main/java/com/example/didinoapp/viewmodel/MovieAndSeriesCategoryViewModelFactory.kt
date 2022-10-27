package com.example.didinoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.didinoapp.data.api.ApiService
import com.example.didinoapp.data.repository.CategoryRepository

class MovieAndSeriesCategoryViewModelFactory(private val apiService: ApiService):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieAndSeriesCategoryViewModel::class.java)){
            return MovieAndSeriesCategoryViewModel(CategoryRepository(apiService)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}