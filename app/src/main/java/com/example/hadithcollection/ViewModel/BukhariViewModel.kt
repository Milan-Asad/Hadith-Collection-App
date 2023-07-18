package com.example.hadithcollection.ViewModel

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hadithcollection.DaggerHilt.APIModule
import com.example.hadithcollection.Model.API_Interface
import com.example.hadithcollection.Model.BukhariDAO
import com.example.hadithcollection.Model.CachedQuotes
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.coroutineScope
//import kotlinx.coroutines.flow.internal.NoOpContinuation.context
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context
import kotlin.random.Random

@HiltViewModel
class BukhariViewModel @Inject constructor(
    // ALL INJECTED ETC
    private val retrofitBuilder: Retrofit.Builder,
    @APIModule.BukhariCollection private val apiInterface: API_Interface,
    private val cachedQuoteDao: BukhariDAO,
    @ApplicationContext private val appContext: Context

) : ViewModel() {

    // VARIABLES
    private val hadithCache: MutableMap<Int, String> = HashMap()
    private var allCachedQuotes: List<CachedQuotes> = emptyList()

    // LIVE DATA
    val BukhariLiveData = MutableLiveData<String>()

    // INITALISING IT
    init {
        fetchAllCachedQuotes()
    }

    // FETCHES ALL THE CACHED QUOTES
    private fun fetchAllCachedQuotes() {
        viewModelScope.launch {
            allCachedQuotes = cachedQuoteDao.getAllCachedQuotes()
        }
    }

    // FETCHES THE BUKHARI HADITHS
    fun fetchBukhariHadith() {
        viewModelScope.launch {
            try {
                // GENERATE RANDOM QUOTE ID
                val quoteId = Random.nextInt()

                // CHECKING IF QUOTE IS CACHED
                if (hadithCache.containsKey(quoteId)) {
                    BukhariLiveData.value = hadithCache[quoteId]
                } else {
                    // IF INTERNET IS CONNECTED IT GETS HADITH (GET REQUEST)
                    if (isInternetConnected()) {
                        val retrofitDataBukhari = apiInterface.getBukhariHadithData()

                        // IF SUCCESSFUL IT SHOWS HADITH
                        if (retrofitDataBukhari.isSuccessful) {
                            val responseBody = retrofitDataBukhari.body()
                            if (responseBody != null) {
                                val hadithData = responseBody.data
                                val hadithText = hadithData.hadith_english

                                // UPDATING UI ON SUCCESSFUL RESPONSE
                                BukhariLiveData.value = hadithText

                                // CACHE QUOTES
                                hadithCache[quoteId] = hadithText

                                // CACHE QUOTE IN DATABASE
                                val cachedQuoteEntity = CachedQuotes(quoteId, hadithText)
                                cachedQuoteDao.cacheQuote(cachedQuoteEntity)

                                // UPDATE THE ALLCACHEDQUOTES LIST
                                allCachedQuotes += cachedQuoteEntity
                            } else {
                                // ERROR MESSAGE
                                Log.d("BukhariViewModel", "RESTART THE APP")
                            }
                        }
                    } else {

                        // NO INTERNET CONNECTION THEN RETRIEVE A RANDOM CACHED QUOTE FROM THE LIST
                        if (allCachedQuotes.isNotEmpty()) {
                            val randomIndex = Random.nextInt(allCachedQuotes.size)
                            val cachedQuote = allCachedQuotes[randomIndex]
                            BukhariLiveData.value = cachedQuote.quote
                            hadithCache[quoteId] = cachedQuote.quote
                        } else {
                            // NO INTERNET + NO CACHED QUOTES
                            Log.d("BukhariViewModel", "NO INTERNET AND NO CACHED QUOTES")
                        }
                    }
                }
            } catch (e: Exception) {
                // MANDATORY CATCH (TRY CATCH)
                Log.d("BukhariViewModel", "ERROR: ${e.message}")
            }
        }
    }

    // INTERNET CONNECTION
    private fun isInternetConnected(): Boolean {
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
