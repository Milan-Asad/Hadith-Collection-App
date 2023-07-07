package com.example.hadithcollection.ViewModel

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.hadithcollection.Model.API_Interface
import com.example.hadithcollection.Model.DataResponseJSON.Data
import com.example.hadithcollection.Model.DataResponseJSON.MyHadithData
import com.example.hadithcollection.View.BASE_URL
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import retrofit2.Response
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TirmidhiViewModelTest {

    @Test
    fun testRetrofitInstance() {

        // BASE URL
        val BASE_URL = "https://random-hadith-generator.vercel.app"

        // CREATING RETROFIT BUILDER
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        // RETRIEVING THE @GET REQUEST CLASS
        val apiInterface: API_Interface = retrofitBuilder.create(API_Interface::class.java)

        // ASSERTING IT
        assertNotNull(apiInterface)

        // Assert that the Retrofit instance has the correct base URL, ignoring trailing slashes
        val actualBaseUrl = retrofitBuilder.baseUrl().toString().trimEnd('/')
        val expectedBaseUrl = BASE_URL.trimEnd('/')
        assertEquals(expectedBaseUrl, actualBaseUrl)
    }

}


