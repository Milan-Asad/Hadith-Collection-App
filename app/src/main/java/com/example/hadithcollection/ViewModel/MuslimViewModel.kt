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
class MuslimViewModel @Inject constructor(
    // NEEDED FOR DEPENDENCY INJECTION
    private val retrofitBuilder: Retrofit.Builder,

    // CALLING API INTERFACE FOR GET REQUEST
    @APIModule.MuslimCollection private val apiInterface: API_Interface
) : ViewModel() {

    // LIVE DATA
    val MuslimLiveData = MutableLiveData<String>()
    val muslimRefnoLiveData = MutableLiveData<String>()

    fun fetchMuslimHadith() {
        viewModelScope.launch {
            try {

                // MAKING VARIABLE TO GET HADITH (GET REQUEST)
                val retrofitDataMuslim = apiInterface.getMuslimHadithData()

                // IF SUCCESSFUL ETC
                if (retrofitDataMuslim.isSuccessful) {
                    val responseBody = retrofitDataMuslim.body()
                    // IF NOT NULL
                    if (responseBody != null) {
                        // VARIABLES
                        val hadithData = responseBody.data
                        val hadithText = hadithData.hadith_english
                        val muslimRefnoText = hadithData.refno

                        // HADITH TEXT BINDING
                        MuslimLiveData.value = hadithText

                        // REFNO TEXT BINDING
                        muslimRefnoLiveData.value = muslimRefnoText
                    } else {
                        // ELSE SHOW ERROR MESSAGE
                        Log.d("Muslim ViewModel", "RESTART THE APP")
                    }
                }

            } // WRITE CATCH HERE
            catch (e: Exception) {
                // MANDATORY CATCH STATEMENT
                Log.d("Muslim ViewModel", "RESTART THE APP: ${e.message}")
            }
        }
    }
}