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
    // NEEDED FOR DEPENDENCY INJECTION
    private val retrofitBuilder: Retrofit.Builder,

    // CALLING API INTERFACE FOR GET REQUEST
    @APIModule.BukhariCollection private val apiInterface: API_Interface
): ViewModel() {

    //LIVE DATA
    val BukhariLiveData = MutableLiveData<String>()

    // FETCHING THE HADITH
    fun fetchBukhariHadith() {
        viewModelScope.launch {
            try {

                // MAKING VARIABLE FOR THE SUSPENDED FUN TO GET HADITH DATA
                val retrofitDataBukhari = apiInterface.getBukhariHadithData()

                // IF SUCCESSFUL ETC
                if (retrofitDataBukhari.isSuccessful) {
                    val responseBody = retrofitDataBukhari.body()
                    // IF NOT NULL
                    if (responseBody != null) {
                        val hadithData = responseBody.data
                        val hadithText = hadithData.hadith_english
                        BukhariLiveData.value = hadithText
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