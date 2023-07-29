package com.hadithCollection.HadithCollection.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadithCollection.HadithCollection.DaggerHilt.APIModule
import com.hadithCollection.HadithCollection.Model.API_Interface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class IbnMajahViewModel @Inject constructor(
    // NEEDED FOR DEPENDENCY INJECTION
    private val retrofitBuilder: Retrofit.Builder,

   // CALLING API INTERFACE FOR GET REQUEST
    @APIModule.IbnMajahCollection private val apiInterface: API_Interface
) : ViewModel() {

    // LIVE DATA VARIABLE
    val IbnMajahLiveData = MutableLiveData<String>()
    val IbnMajahRefnoLiveData = MutableLiveData<String>()


    fun fetchIbnMajahHadiths() {
        viewModelScope.launch {
            try {
                // MAKING VARIABLE TO GET HADITH (GET REQUEST)
                val retrofitDataMuslim = apiInterface.getIbnMajahHadithData()

                // IF SUCCESSFUL ETC
                if (retrofitDataMuslim.isSuccessful) {
                    val responseBody = retrofitDataMuslim.body()
                    // IF NOT NULL
                    if (responseBody != null) {
                        // VARIABLES
                        val hadithData = responseBody.data
                        val hadithText = hadithData.hadith_english
                        val ibnMajahRefnoText = hadithData.refno

                        // BINDING IT
                        IbnMajahLiveData.value = hadithText
                        IbnMajahRefnoLiveData.value = ibnMajahRefnoText
                    } else {
                        // ELSE SHOW ERROR MESSAGE
                        Log.d("Muslim ViewModel", "RESTART THE APP")

                    }
                }
            } // WRITE CATCH HERE
            catch (e: Exception) {
                // MANDATORY CATCH STATEMENT
                //Log.d("Ibn Majah ViewModel", "RESTART THE APP: ${e.message}")
                Log.d("Ibn Majah ViewModel", "ERROR! NO INTERNET CONNECTION: ${e.message}")
            }

        }
    }
}