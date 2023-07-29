package com.hadithCollection.HadithCollection.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
//import com.HadithCollection.hadithcollection.databinding.ActivityAbuDawudCollectionBinding
import com.hadithCollection.HadithCollection.ViewModel.AbuDawudViewModel
import com.hadithCollection.HadithCollection.databinding.ActivityAbuDawudCollectionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AbuDawudCollection : AppCompatActivity() {

    // BINDING VAR
    lateinit var binding: ActivityAbuDawudCollectionBinding

    // VIEW MODEL VAR
    lateinit var abudawudviewmodel: AbuDawudViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAbuDawudCollectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // VIEW MODEL PROVIDER
        abudawudviewmodel = ViewModelProvider(this).get(AbuDawudViewModel::class.java)

        // SHOWING HADITH ON BUTTON CLICK
        binding.abudawudBtn.setOnClickListener {
            abudawudviewmodel.fetchAbuDawudHadith()
            binding.abuDawudScrollView.smoothScrollTo(0, 0)
            overridePendingTransition(0, 0)
        }

        // ABU DAWUD HADITH
        abudawudviewmodel.AbuDawudLiveData.observe(this, Observer { hadithText ->
            binding.abudawudhadithTxtView.text = hadithText
        })

        // ABU DAWUD REF-NUMBER
        abudawudviewmodel.AbuDawudRefnoLiveData.observe(this, Observer { hadithRefno ->
            binding.abuDawudHadithRefno.text = hadithRefno
        })
    }
}