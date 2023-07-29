package com.hadithCollection.HadithCollection.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hadithCollection.HadithCollection.ViewModel.IbnMajahViewModel
import com.hadithCollection.HadithCollection.databinding.ActivityIbnMajahCollectionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IbnMajahCollection : AppCompatActivity() {

    // VIEW MODEL
    lateinit var ibnMajahViewModel: IbnMajahViewModel

    // BINDING VAR
    lateinit var binding: ActivityIbnMajahCollectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIbnMajahCollectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ATTACHING VIEWMODEL
        ibnMajahViewModel = ViewModelProvider(this).get(IbnMajahViewModel::class.java)

        // SHOWING HADITH ON BUTTON CLICK
        binding.ibnmajahBtn.setOnClickListener {
            ibnMajahViewModel.fetchIbnMajahHadiths()
            binding.ibnMajahScrollView.smoothScrollTo(0, 0)
            overridePendingTransition(0, 0)
        }

        // IBN MAJAH LIVE DATA
        ibnMajahViewModel.IbnMajahLiveData.observe(this, Observer { hadithText ->
            binding.ibnmajahhadithTxtView.text = hadithText
        })

        // IBN MAJAH REF-NUMBER
        ibnMajahViewModel.IbnMajahRefnoLiveData.observe(this, Observer { hadithRefno ->
            binding.ibnMajahHadithRefno.text = hadithRefno
        })
    }
}