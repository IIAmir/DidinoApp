package com.example.didinoapp.data.repository

import com.example.didinoapp.data.api.ApiService
import com.example.didinoapp.data.model.MovieAndSeriesModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRepository(
    private val apiService: ApiService
) {

    private suspend fun searchMovie(searchQuery: String):MutableList<MovieAndSeriesModel>{
        return apiService.searchRepos(searchQuery)
    }

    suspend fun getRepos(searchQuery: String): Flow<MutableList<MovieAndSeriesModel>> {
        return flow {
            emit(searchMovie(searchQuery))
        }
    }

}