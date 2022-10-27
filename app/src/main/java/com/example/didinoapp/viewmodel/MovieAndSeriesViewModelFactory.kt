package com.example.didinoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.didinoapp.data.api.ApiService
import com.example.didinoapp.data.repository.MainRepository

class MovieAndSeriesViewModelFactory(private val apiService: ApiService):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieAndSeriesViewModel::class.java)){
            return MovieAndSeriesViewModel(MainRepository(apiService)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}