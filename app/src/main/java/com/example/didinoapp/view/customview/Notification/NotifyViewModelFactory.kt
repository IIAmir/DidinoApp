package com.example.didinoapp.view.customview.Notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.didinoapp.data.api.ApiService
import com.example.didinoapp.data.repository.NotifyRepository

class NotifyViewModelFactory(private val apiService: ApiService):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotifyViewModel::class.java)){
            return NotifyViewModel(NotifyRepository(apiService)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}