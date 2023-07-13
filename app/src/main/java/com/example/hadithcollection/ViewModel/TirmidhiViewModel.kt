package com.example.hadithcollection.ViewModel

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
//import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hadithcollection.DaggerHilt.APIModule
import com.example.hadithcollection.Model.DataResponseJSON.MyHadithData
import com.example.hadithcollection.Model.API_Interface
import com.example.hadithcollection.View.BASE_URL
import com.example.hadithcollection.databinding.ActivityTirmidhiCollectionBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

@HiltViewModel
class TirmidhiViewModel @Inject constructor(
    // NEEDED FOR DEPENDENCY INJECTION
    private val retrofitBuilder: Retrofit.Builder,

    // CALLING API INTERFACE FOR GET REQUEST
    @APIModule.TirmidhiCollection private val apiInterface: API_Interface
) : ViewModel() {

    // LIVE DATA
    val hadithLiveData = MutableLiveData<String>()

    // FETCHING HADITH
    fun fetchHadith() {
        viewModelScope.launch {
            try {

                // MAKING VARIABLE FOR THE SUSPENDED FUN TO GET HADITH DATA
                val retrofitData = apiInterface.getHadithData()

                // IF SUCCESSFUL ETC
                if (retrofitData.isSuccessful) {
                    val responseBody = retrofitData.body()
                    // IF NOT NULL
                    if (responseBody != null) {
                        val hadithData = responseBody.data
                        val hadithText = hadithData.hadith_english
                        hadithLiveData.value = hadithText
                    } else {
                        // ELSE SHOW ERROR MESSAGE
                        Log.d("TirmidhiViewModel", "RESTART THE APP")
                    }
                }
            } catch (e: Exception) {
                // MANDATORY CATCH STATEMENT
                Log.d("TirmidhiViewModel", "RESTART THE APP: ${e.message}")
            }
        }
    }
}

