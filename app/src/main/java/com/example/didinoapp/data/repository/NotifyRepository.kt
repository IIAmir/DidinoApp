package com.example.didinoapp.data.repository

import com.example.didinoapp.data.api.ApiService
import com.example.didinoapp.data.model.MovieAndSeriesModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call

class NotifyRepository(private val apiService: ApiService) {

    private suspend fun notifyData(): MutableList<MovieAndSeriesModel> {
        return apiService.notifyData()
    }

    suspend fun getNotifyData():Flow<MutableList<MovieAndSeriesModel>> {
        return flow {
            emit(notifyData())
        }
    }
}