package com.example.didinoapp.viewmodel

import androidx.lifecycle.*
import com.example.didinoapp.data.model.MovieAndSeriesCategoryModel
import com.example.didinoapp.data.repository.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieAndSeriesCategoryViewModel(private val categoryRepository: CategoryRepository) :
    ViewModel() {
    private val movieAndSeriesCategoryMutableLiveData =
        MutableLiveData<List<MovieAndSeriesCategoryModel>>()

    val movieAndSeriesCategoryLiveData: LiveData<List<MovieAndSeriesCategoryModel>> get() = movieAndSeriesCategoryMutableLiveData

    fun getCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            movieAndSeriesCategoryMutableLiveData.postValue(categoryRepository.getRepositoryCategory())
        }
    }
}