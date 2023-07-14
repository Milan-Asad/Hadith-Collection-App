package com.example.hadithcollection.Model

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [CachedQuotes::class], version = 1, exportSchema = false)
abstract class BukhariDatabase : RoomDatabase() {
    abstract fun bukhariDAO(): BukhariDAO
}