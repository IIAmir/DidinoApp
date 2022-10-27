package com.example.didinoapp.data.repository

import com.example.didinoapp.data.api.ApiService

class CategoryRepository(private val apiService: ApiService) {
    suspend fun getRepositoryCategory() = apiService.getCategory()
}
