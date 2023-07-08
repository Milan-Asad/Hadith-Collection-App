package com.example.hadithcollection.ViewModel

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hadithcollection.Model.DataResponseJSON.MyHadithData
import com.example.hadithcollection.Model.API_Interface
import com.example.hadithcollection.View.BASE_URL
import com.example.hadithcollection.databinding.ActivityTirmidhiCollectionBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class TirmidhiViewModel : ViewModel() {
    private val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(API_Interface::class.java)

    val hadithLiveData = MutableLiveData<String>()

    fun fetchHadith() {
        viewModelScope.launch {
            try {
                val retrofitData = retrofitBuilder.getHadithData()

                if (retrofitData.isSuccessful) {
                    val responseBody = retrofitData.body()
                    if (responseBody != null) {
                        val hadithData = responseBody.data
                        val hadithText = hadithData.hadith_english
                        hadithLiveData.value = hadithText
                    } else {
                        Log.d("TirmidhiViewModel", "RESTART THE APP")
                    }
                }
            } catch (e: Exception) {
                Log.d("TirmidhiViewModel", "RESTART THE APP: ${e.message}")
            }
        }
    }
}

