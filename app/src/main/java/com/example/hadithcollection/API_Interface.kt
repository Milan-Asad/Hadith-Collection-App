package com.example.hadithcollection

import com.example.hadithcollection.HadithResponse.MyHadithData
import retrofit2.Call
import retrofit2.http.GET

interface API_Interface {
    @GET("tirmidhi")
    fun getHadithData(): Call<MyHadithData>
}