package com.example.hadithcollection.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hadithcollection.ViewModel.TirmidhiViewModel
import com.example.hadithcollection.databinding.ActivityTirmidhiCollectionBinding

// https://random-hadith-generator.vercel.app/tirmidhi/- MAIN LINK

const val BASE_URL = "https://random-hadith-generator.vercel.app"

class TirmidhiCollection : AppCompatActivity() {

    private lateinit var binding: ActivityTirmidhiCollectionBinding
    private lateinit var tirmidhiViewModel: TirmidhiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTirmidhiCollectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tirmidhiViewModel = ViewModelProvider(this).get(TirmidhiViewModel::class.java)

        binding.hadithBtn.setOnClickListener {
            tirmidhiViewModel.fetchHadith()
        }

        tirmidhiViewModel.hadithLiveData.observe(this, Observer { hadithText ->
            binding.tirmidhihadithTxtView.text = hadithText
        })
    }
}