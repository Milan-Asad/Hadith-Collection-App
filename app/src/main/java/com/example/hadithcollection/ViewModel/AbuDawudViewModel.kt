package com.example.hadithcollection.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hadithcollection.DaggerHilt.APIModule
import com.example.hadithcollection.Model.API_Interface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class AbuDawudViewModel @Inject constructor(
    // NEEDED FOR DEPENDENCY INJECTION
    private val retrofitBuilder: Retrofit.Builder,

    // CALLING API INTERFACE FOR GET REQUEST
    @APIModule.AbuDawudCollection private val apiInterface: API_Interface
) : ViewModel() {

    val AbuDawudLiveData = MutableLiveData<String>()

    fun fetchAbuDawudHadith() {
        viewModelScope.launch {

            try {
                // MAKING RETROFIT BUILDER
                val retrofitdataAbuDawud = apiInterface.getAbuDawudHadithData()

                // IF SUCCESSFUL ETC
                if (retrofitdataAbuDawud.isSuccessful) {
                    val responseBody = retrofitdataAbuDawud.body()
                    // IF NOT NULL
                    if (responseBody != null) {
                        val hadithData = responseBody.data
                        val hadithText = hadithData.hadith_english
                        AbuDawudLiveData.value = hadithText
                    } else {
                        // ELSE SHOW ERROR MESSAGE
                        Log.d("Abu Dawud ViewModel", "RESTART THE APP")
                    }
                }
            }
            // WRITE CATCH HERE
            catch (e: Exception) {
                // MANDATORY CATCH STATEMENT
                Log.d("Abu Dawud ViewModel", "RESTART THE APP: ${e.message}")
            }
        }
    }
}