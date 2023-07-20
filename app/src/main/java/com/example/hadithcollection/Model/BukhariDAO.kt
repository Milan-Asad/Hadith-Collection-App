package com.example.hadithcollection.Model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface BukhariDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun cacheQuote(quote: CachedQuotes)

    // SELECTS CACHED QUOTES USING QUOTE ID
    @Query("SELECT * FROM cached_quotes WHERE id = :quoteId")
    suspend fun getCachedQuote(quoteId: Int): CachedQuotes?

    // SELECTS CACHED QUOTES
    @Query("SELECT * FROM cached_quotes")
    suspend fun getAllCachedQuotes(): List<CachedQuotes>
}