package com.example.hadithcollection.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hadithcollection.R
import com.example.hadithcollection.ViewModel.BukhariViewModel
import com.example.hadithcollection.databinding.ActivityBukhariCollectionBinding
import com.example.hadithcollection.databinding.ActivityTirmidhiCollectionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BukhariCollection : AppCompatActivity() {

    // VIEW MODEL VAR
    lateinit var bukhariViewModel: BukhariViewModel

    // VIEW BINDING VAR
    private lateinit var binding: ActivityBukhariCollectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBukhariCollectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // VIEW MODEL PROVIDER
        bukhariViewModel = ViewModelProvider(this).get(BukhariViewModel::class.java)

        // SHOWING HADITH ON BUTTON CLICK
        binding.bukhariBtn.setOnClickListener {
            bukhariViewModel.fetchBukhariHadith()
            binding.bukhariScrollView.smoothScrollTo(0, 0)
            overridePendingTransition(0, 0)
        }

        // HADITH TEXT
        bukhariViewModel.BukhariLiveData.observe(this, Observer { hadithText ->
            binding.bukharihadithTxtView.text = hadithText
        })

        // HADITH REF-NUMBER
        bukhariViewModel.BukhariRefnoLiveData.observe(this, Observer { hadithRefno ->
            binding.bukhariHadithRefno.text = hadithRefno
        })


    }
}