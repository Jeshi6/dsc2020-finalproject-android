package com.example.dsc2020_finalproject_android

import com.example.dsc2020_finalproject_android.home.model.ProvincesResponse
import com.example.dsc2020_finalproject_android.home.model.TotalResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("https://covid19.mathdro.id/api/countries/IDN")
    fun getTotal() : Call<TotalResponse>

    @GET("api/provinsi")
    fun getProvinces() : Call<ProvincesResponse>

}