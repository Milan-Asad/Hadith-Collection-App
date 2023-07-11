package com.example.hadithcollection.DaggerHilt

import com.example.hadithcollection.Model.API_Interface
import com.example.hadithcollection.View.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object APIModule {
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
    }

    @Provides
    fun provideAPIInterface(retrofitBuilder: Retrofit.Builder): API_Interface {
        return retrofitBuilder
            .build()
            .create(API_Interface::class.java)
    }
}
