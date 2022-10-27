package com.example.didinoapp.data.repository

import com.example.didinoapp.data.api.ApiService

class MainRepository(private val apiService: ApiService) {

    suspend fun getAllMovieAndSeries() = apiService.getAllMovieAndSeries()

    suspend fun getRepositoryAllSeries() = apiService.getAllSeries()

    suspend fun getRepositoryAllMoviesPersian() = apiService.getRepositoryAllMoviesPersian()

    suspend fun getRepositoryGallery() = apiService.getGallery()

    suspend fun getRepositorySpecial() = apiService.getSpecial()

    suspend fun getRepositoryNew() = apiService.getNew()

    suspend fun getRepositoryFree() = apiService.getFree()

    suspend fun getRepositoryPersianSeries() = apiService.getPersianSeries()

    suspend fun getRepositoryForeignSeries() = apiService.getForeignSeries()

    suspend fun getRepositoryAction() = apiService.getAction()

    suspend fun getRepositoryHistorical() = apiService.getHistorical()

    suspend fun getRepositoryRomantic() = apiService.getRomantic()

    suspend fun getRepositoryFamily() = apiService.getFamily()

    suspend fun getRepositoryDocumentary() = apiService.getDocumentary()

    suspend fun getRepositoryComedy() = apiService.getComedy()

    suspend fun getRepositoryScience() = apiService.getScience()

    suspend fun getRepositoryScary() = apiService.getScary()

    suspend fun getRepositoryAnimation() = apiService.getAnimation()

}