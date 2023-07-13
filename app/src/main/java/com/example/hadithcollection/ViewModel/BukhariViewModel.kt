package com.example.hadithcollection.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hadithcollection.DaggerHilt.APIModule
import com.example.hadithcollection.Model.API_Interface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class BukhariViewModel @Inject constructor(
    private val retrofitBuilder: Retrofit.Builder,
    @APIModule.BukhariCollection private val apiInterface: API_Interface
): ViewModel() {

    //LIVE DATA
    val BukhariLiveData = MutableLiveData<String>()

    // FETCHING THE HADITH
    fun fetchBukhariHadith() {
        viewModelScope.launch {
            try {
                val retrofitDataBukhari = apiInterface.getBukhariHadithData()

                if (retrofitDataBukhari.isSuccessful) {
                    val responseBody = retrofitDataBukhari.body()
                    if (responseBody != null) {
                        val hadithData = responseBody.data
                        val hadithText = hadithData.hadith_english
                        BukhariLiveData.value = hadithText
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