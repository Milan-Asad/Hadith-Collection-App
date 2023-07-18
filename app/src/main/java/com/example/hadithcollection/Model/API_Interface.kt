package com.example.hadithcollection.Model

import com.example.hadithcollection.Model.DataResponseJSON.MyHadithData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

// https://random-hadith-generator.vercel.app

interface API_Interface {


    // TIRMIDHI GET REQUEST
    @GET("tirmidhi")
    suspend fun getTirmidhiHadithData(): Response<MyHadithData>

    // BUKHARI GET REQUEST
    @GET("bukhari")
    suspend fun getBukhariHadithData(): Response<MyHadithData>

    // MUSLIM GET REQUEST
    @GET("muslim")
    suspend fun getMuslimHadithData(): Response<MyHadithData>

    // ABU DAWUD GET REUEST
    @GET("abudawud")
    suspend fun getAbuDawudHadithData(): Response<MyHadithData>
}