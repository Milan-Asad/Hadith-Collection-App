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

    // RETROFIT BUILDER (USE SINGLETON BECAUSE WE ONLY CREATE)
    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
    }

    // TIRMIDHI API INTERFACE
    @Provides
    @TirmidhiCollection
    fun provideAPIInterface(retrofitBuilder: Retrofit.Builder): API_Interface {
        return retrofitBuilder
            .build()
            .create(API_Interface::class.java)
    }

    // TIRMIDHI CUSTOM ANNOTATIONS
    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class TirmidhiCollection


    // BUKHARI API INTERFACE
    @Provides
    @BukhariCollection
    fun provideBukhariAPIInterface(retrofitBuilder: Retrofit.Builder): API_Interface {
        return retrofitBuilder
            .build()
            .create(API_Interface::class.java)
    }

    // BUKHARI ANNOTATION
    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class BukhariCollection


    // MUSLIM API INTERFACE
    @Provides
    @MuslimCollection
    fun provideMuslimAPIInteface(retrofitBuilder: Retrofit.Builder): API_Interface {
        return retrofitBuilder
            .build()
            .create(API_Interface::class.java)
    }

    // MUSLIM ANNOTATION
    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class MuslimCollection


    // ABU DAWUD API INTERFACE
    @Provides
    @AbuDawudCollection
    fun provideAbuDawudAPIInterface(retrofitBuilder: Retrofit.Builder): API_Interface {
        return retrofitBuilder
            .build()
            .create(API_Interface::class.java)
    }

    // ABU DAWUD ANNOTATION
    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class AbuDawudCollection


    // IBN MAJAH API INTERFACE
    @Provides
    @IbnMajahCollection
    fun provideIbnMajahAPIInterface(retrofitBuilder: Builder): API_Interface {
        return retrofitBuilder
            .build()
            .create(API_Interface::class.java)
    }

    // IBN MAJAH API INTERFACE
    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class IbnMajahCollection
}

