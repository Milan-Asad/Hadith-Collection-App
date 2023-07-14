package com.example.hadithcollection.Model

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BukhariDAOModule {
    @Provides
    @Singleton
    fun provideDatabase(context: Context): BukhariDatabase {
        return Room.databaseBuilder(
            context,
            BukhariDatabase::class.java,
            "bukhari_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideBukhariDAO(database: BukhariDatabase): BukhariDAO {
        return database.bukhariDAO()
    }

    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext appContext: Context): Context {
        return appContext
    }
}









