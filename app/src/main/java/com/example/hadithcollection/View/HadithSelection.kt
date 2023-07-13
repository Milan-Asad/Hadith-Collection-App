package com.example.hadithcollection.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hadithcollection.databinding.ActivityHadithSelectionBinding

// https://random-hadith-generator.vercel.app/ - Main Master Link


class HadithSelection : AppCompatActivity() {

    private lateinit var binding: ActivityHadithSelectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // SETTING VIEW BINDING
        binding = ActivityHadithSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TIRMIDHI PAGE
        binding.tirmidhiBtn.setOnClickListener {
            val intent = Intent(this, TirmidhiCollection::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }

        // BUKHARI PAGE
        binding.bukhariBtn.setOnClickListener {
            val intent = Intent(this, BukhariCollection::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
    }
}