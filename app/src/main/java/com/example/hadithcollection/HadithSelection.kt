package com.example.hadithcollection

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

        binding.tirmidhiBtn.setOnClickListener {
            val Intent = Intent(this, TirmidhiCollection::class.java)
            startActivity(Intent)

            // NO ANIMATION
            overridePendingTransition(0, 0)
        }


    }


}