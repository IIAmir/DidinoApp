package com.example.didinoapp.view.fragments.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.didinoapp.data.model.MovieAndSeriesModel
import com.example.didinoapp.data.repository.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(private val searchRepository: SearchRepository) : ViewModel() {

    private val searchMutableLiveData = MutableLiveData<MutableList<MovieAndSeriesModel>>()
    val searchLiveData: LiveData<MutableList<MovieAndSeriesModel>> get() = searchMutableLiveData

    fun getRepos(searchQuery: String) {
        viewModelScope.launch(Dispatchers.IO) {
            searchRepository.getRepos(searchQuery).collect {
                searchMutableLiveData.postValue(it)
            }
        }
    }
}