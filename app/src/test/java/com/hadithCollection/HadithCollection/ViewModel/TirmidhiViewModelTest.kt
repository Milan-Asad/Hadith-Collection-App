package com.hadithCollection.HadithCollection.ViewModel

//import com.example.HadithCollection.Model.API_Interface
import com.hadithCollection.HadithCollection.Model.API_Interface
import org.junit.Assert.*
import org.junit.Test
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


