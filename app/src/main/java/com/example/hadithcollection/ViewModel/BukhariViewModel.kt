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
    private val retrofitBuilder: Retrofit.Builder,
    @APIModule.BukhariCollection private val apiInterface: API_Interface,
    private val cachedQuoteDao: BukhariDAO,

    //private val appContext: Context
    @ApplicationContext private val appContext: Context

) : ViewModel() {

    private val hadithCache: MutableMap<Int, String> = HashMap()
    private var allCachedQuotes: List<CachedQuotes> = emptyList()

    val BukhariLiveData = MutableLiveData<String>()

    init {
        fetchAllCachedQuotes()
    }

    private fun fetchAllCachedQuotes() {
        viewModelScope.launch {
            allCachedQuotes = cachedQuoteDao.getAllCachedQuotes()
        }
    }

    fun fetchBukhariHadith() {
        viewModelScope.launch {
            try {
                val quoteId = Random.nextInt() // Generate a random quoteId

                // Check if the quote is already cached
                if (hadithCache.containsKey(quoteId)) {
                    BukhariLiveData.value = hadithCache[quoteId]
                } else {
                    if (isInternetConnected()) {
                        val retrofitDataBukhari = apiInterface.getBukhariHadithData()

                        if (retrofitDataBukhari.isSuccessful) {
                            val responseBody = retrofitDataBukhari.body()
                            if (responseBody != null) {
                                val hadithData = responseBody.data
                                val hadithText = hadithData.hadith_english
                                BukhariLiveData.value = hadithText

                                // Cache the quote
                                hadithCache[quoteId] = hadithText

                                // Cache the quote in the database
                                val cachedQuoteEntity = CachedQuotes(quoteId, hadithText)
                                cachedQuoteDao.cacheQuote(cachedQuoteEntity)

                                // Update the allCachedQuotes list
                                allCachedQuotes += cachedQuoteEntity
                            } else {
                                Log.d("BukhariViewModel", "RESTART THE APP")
                            }
                        }
                    } else {
                        // No internet connection, retrieve a random cached quote from the list
                        if (allCachedQuotes.isNotEmpty()) {
                            val randomIndex = Random.nextInt(allCachedQuotes.size)
                            val cachedQuote = allCachedQuotes[randomIndex]
                            BukhariLiveData.value = cachedQuote.quote
                            hadithCache[quoteId] = cachedQuote.quote
                        } else {
                            Log.d("BukhariViewModel", "NO INTERNET AND NO CACHED QUOTES")
                        }
                    }
                }
            } catch (e: Exception) {
                Log.d("BukhariViewModel", "ERROR: ${e.message}")
            }
        }
    }

    private fun isInternetConnected(): Boolean {
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
