package com.example.didinoapp.data.api

import com.example.didinoapp.utils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private fun getApiClient(): Retrofit {

        val logIn: HttpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttp: OkHttpClient = OkHttpClient().newBuilder().addInterceptor(logIn).build()


        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(okHttp).build()
    }

    val apiService: ApiService = getApiClient().create(ApiService::class.java)
}