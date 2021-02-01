package com.example.dsc2020_finalproject_android.home.model

import com.google.gson.annotations.SerializedName

data class TotalResponse(
    val confirmed : DataResponse,
    val recovered : DataResponse,
    val deaths : DataResponse
)

data class DataResponse (val value : Int, val detail: String)
