package com.example.hadithcollection.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hadithcollection.R
import com.example.hadithcollection.ViewModel.AbuDawudViewModel
import com.example.hadithcollection.ViewModel.BukhariViewModel
import com.example.hadithcollection.databinding.ActivityAbuDawudCollectionBinding
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

        abudawudviewmodel = ViewModelProvider(this).get(AbuDawudViewModel::class.java)

        binding.abudawudBtn.setOnClickListener {
            abudawudviewmodel.fetchAbuDawudHadith()
            binding.scrollView2.smoothScrollTo(0, 0)
            overridePendingTransition(0, 0)
        }

        abudawudviewmodel.AbuDawudLiveData.observe(this, Observer { hadithText ->
            binding.abudawudhadithTxtView.text = hadithText
        })
    }
}