package com.hadithCollection.HadithCollection.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hadithCollection.HadithCollection.ViewModel.TirmidhiViewModel
import com.hadithCollection.HadithCollection.databinding.ActivityTirmidhiCollectionBinding
import dagger.hilt.android.AndroidEntryPoint

// https://random-hadith-generator.vercel.app/tirmidhi/- MAIN LINK

const val BASE_URL = "https://random-hadith-generator.vercel.app"

@AndroidEntryPoint
class TirmidhiCollection : AppCompatActivity() {

    lateinit var tirmidhiViewModel: TirmidhiViewModel
    private lateinit var binding: ActivityTirmidhiCollectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTirmidhiCollectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tirmidhiViewModel = ViewModelProvider(this).get(TirmidhiViewModel::class.java)

        binding.hadithBtn.setOnClickListener {
            tirmidhiViewModel.fetchHadith()
            binding.scrollview.smoothScrollTo(0, 0)
            overridePendingTransition(0, 0)
        }

        tirmidhiViewModel.hadithLiveData.observe(this, Observer { hadithText ->
            binding.tirmidhihadithTxtView.text = hadithText
        })

        tirmidhiViewModel.hadithRefnoLiveData.observe(this, Observer { hadithRefno ->
            binding.tirmidhiHadithRefno.text = hadithRefno
        })
    }
}

