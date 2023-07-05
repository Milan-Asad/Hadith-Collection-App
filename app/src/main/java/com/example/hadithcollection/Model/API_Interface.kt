package com.example.hadithcollection.Model

import com.example.hadithcollection.Model.DataResponseJSON.MyHadithData
import retrofit2.Call
import retrofit2.http.GET

interface API_Interface {
    @GET("tirmidhi")
    fun getHadithData(): Call<MyHadithData>
}