package com.example.hadithcollection.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cached_quotes")
data class CachedQuotes(
    @PrimaryKey val id: Int = 0,
    val quote: String = ""
)
