package com.example.dsc2020_finalproject_android

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    val retrofit = Retrofit.Builder().
            addConverterFactory(GsonConverterFactory.create()).baseUrl("https://indonesia-covid-19.mathdro.id/").build()

    val apiService = retrofit.create(ApiService::class.java)

}