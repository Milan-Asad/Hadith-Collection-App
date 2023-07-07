package com.example.hadithcollection.View

import com.example.hadithcollection.Model.API_Interface
import com.example.hadithcollection.ViewModel.TirmidhiViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//class AppModule {
//    // ...
//
//    @Provides
//    @Singleton
//    fun provideTirmidhiViewModel(
//        apiInterface: API_Interface,
//        retrofitBuilder: Retrofit
//    ): TirmidhiViewModel {
//        return TirmidhiViewModel(apiInterface, retrofitBuilder)
//    }
//}