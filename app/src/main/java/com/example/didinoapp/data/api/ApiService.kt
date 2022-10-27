package com.example.didinoapp.data.api

import com.example.didinoapp.data.model.MovieAndSeriesCategoryModel
import com.example.didinoapp.data.model.MovieAndSeriesModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // Didino Fragment

    @GET("getAll.php")
    suspend fun getAllMovieAndSeries(): List<MovieAndSeriesModel>

    @GET("getAllSeries.php")
    suspend fun getAllSeries(): List<MovieAndSeriesModel>

    @GET("getAllMoviesPersian.php")
    suspend fun getRepositoryAllMoviesPersian(): List<MovieAndSeriesModel>

    @GET("getGallery.php")
    suspend fun getGallery(): List<MovieAndSeriesModel>

    @GET("getSpecial.php")
    suspend fun getSpecial(): List<MovieAndSeriesModel>

    @GET("getNew.php")
    suspend fun getNew(): List<MovieAndSeriesModel>

    @GET("getFree.php")
    suspend fun getFree(): List<MovieAndSeriesModel>

    @GET("getPersianSeries.php")
    suspend fun getPersianSeries(): List<MovieAndSeriesModel>

    @GET("getForeignSeries.php")
    suspend fun getForeignSeries(): List<MovieAndSeriesModel>

    @GET("getAction.php")
    suspend fun getAction(): List<MovieAndSeriesModel>

    @GET("getHistorical.php")
    suspend fun getHistorical(): List<MovieAndSeriesModel>

    @GET("getRomantic.php")
    suspend fun getRomantic(): List<MovieAndSeriesModel>

    @GET("getFamily.php")
    suspend fun getFamily(): List<MovieAndSeriesModel>

    @GET("getDocumentary.php")
    suspend fun getDocumentary(): List<MovieAndSeriesModel>

    @GET("getComedy.php")
    suspend fun getComedy(): List<MovieAndSeriesModel>

    @GET("getScience.php")
    suspend fun getScience(): List<MovieAndSeriesModel>

    @GET("getScary.php")
    suspend fun getScary(): List<MovieAndSeriesModel>

    @GET("getAnimation.php")
    suspend fun getAnimation(): List<MovieAndSeriesModel>

    // Category Fragment

    @GET("getCategory.php")
    suspend fun getCategory(): List<MovieAndSeriesCategoryModel>

    // Search Fragment

    @GET("getAll.php")
    suspend fun searchRepos(@Query("name_persian_SE") searchQuery: String): MutableList<MovieAndSeriesModel>

    // Notify View

    @GET("getAll.php")
    suspend fun notifyData(): MutableList<MovieAndSeriesModel>
}