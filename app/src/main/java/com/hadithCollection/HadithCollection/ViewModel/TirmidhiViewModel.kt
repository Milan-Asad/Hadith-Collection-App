package com.hadithCollection.HadithCollection.ViewModel

import android.util.Log
//import androidx.hilt.lifecycle.ViewModelInject
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
class TirmidhiViewModel @Inject constructor(
    // NEEDED FOR DEPENDNECY INJECTION
    private val retrofitBuilder: Retrofit.Builder,

    // CALLING API INTERFACE FOR GET REQUEST
    @APIModule.TirmidhiCollection private val apiInterface: API_Interface
) : ViewModel() {

    // LIVE DATA VARIABLES
    val hadithLiveData = MutableLiveData<String>()
    val hadithRefnoLiveData = MutableLiveData<String>()

    // FETCHING HADITH
    fun fetchHadith() {
        viewModelScope.launch {
            try {
                // MAKING VARIABLE FOR THE SUSPENDED FUN TO GET HADITH DATA
                val retrofitData = apiInterface.getTirmidhiHadithData()

                // IF SUCCESSFUL ETC
                if (retrofitData.isSuccessful) {
                    val responseBody = retrofitData.body()

                    // IF NOT NULL
                    if (responseBody != null) {
                        val hadithData = responseBody.data
                        val hadithText = hadithData.hadith_english
                        val hadithRefnoText = hadithData.refno

                        hadithLiveData.value = hadithText
                        hadithRefnoLiveData.value = hadithRefnoText
                    } else {

                        // ELSE SHOW ERROR MESSAGE
                        Log.d("TirmidhiViewModel", "RESTART THE APP")
                    }
                }
            }
            // MANDATORY CATCH STATEMENT
            catch (e: Exception) {
                Log.d("TirmidhiViewModel", "RESTART THE APP: ${e.message}")
            }
        }
    }
}



