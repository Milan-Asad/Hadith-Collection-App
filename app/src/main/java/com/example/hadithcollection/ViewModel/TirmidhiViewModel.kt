package com.example.hadithcollection.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hadithcollection.Model.DataResponseJSON.MyHadithData
import com.example.hadithcollection.Model.API_Interface
import com.example.hadithcollection.View.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TirmidhiViewModel : ViewModel() {

     var retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(API_Interface::class.java)

    val hadithLiveData = MutableLiveData<String>()

    fun fetchHadith() {
        val retrofitData = retrofitBuilder.getHadithData()

        retrofitData.enqueue(object : Callback<MyHadithData> {
            override fun onResponse(call: Call<MyHadithData>, response: Response<MyHadithData>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        val hadithData = responseBody.data
                        val hadithText = hadithData.hadith_english
                        hadithLiveData.value = hadithText
                    } else {
                        Log.d("TirmidhiViewModel", "RESTART THE APP")
                    }
                }
            }

            override fun onFailure(call: Call<MyHadithData>, t: Throwable) {
                Log.d("TirmidhiViewModel", "RESTART THE APP: ${t.message}")
            }
        })
    }
}