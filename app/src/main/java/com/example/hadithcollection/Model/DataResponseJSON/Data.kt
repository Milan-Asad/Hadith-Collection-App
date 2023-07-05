package com.example.hadithcollection.Model.DataResponseJSON

data class Data(
    val book: String,
    val bookName: String,
    val chapterName: String,
    val hadith_english: String,
    val header: String,
    val id: Int,
    val refno: String,   /// originall string
)