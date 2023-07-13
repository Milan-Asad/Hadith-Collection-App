package com.example.hadithcollection.DaggerHilt

import com.example.hadithcollection.Model.API_Interface
import com.example.hadithcollection.View.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object APIModule {
    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
    }

    @Provides
    @TirmidhiCollection
    fun provideAPIInterface(retrofitBuilder: Retrofit.Builder): API_Interface {
        return retrofitBuilder
            .build()
            .create(API_Interface::class.java)
    }

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class TirmidhiCollection

    @Provides
    @BukhariCollection
    fun provideBukhariAPIInterface(retrofitBuilder: Retrofit.Builder): API_Interface {
        return retrofitBuilder
            .build()
            .create(API_Interface::class.java)
    }

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class BukhariCollection
}

