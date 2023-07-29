package com.hadithCollection.HadithCollection.Model


import com.hadithCollection.HadithCollection.Model.DataResponseJSON.MyHadithData
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

    // ABU DAWUD GET REQUEST
    @GET("abudawud")
    suspend fun getAbuDawudHadithData(): Response<MyHadithData>

    // IBN MAJAH GET REQUEST
    @GET("ibnmajah")
    suspend fun getIbnMajahHadithData(): Response<MyHadithData>
}