package com.example.hadithcollection.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hadithcollection.R
import com.example.hadithcollection.ViewModel.BukhariViewModel
import com.example.hadithcollection.ViewModel.IbnMajahViewModel
import com.example.hadithcollection.databinding.ActivityIbnMajahCollectionBinding
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

        ibnMajahViewModel = ViewModelProvider(this).get(IbnMajahViewModel::class.java)

        binding.ibnmajahBtn.setOnClickListener {
            ibnMajahViewModel.fetchIbnMajahHadiths()
            binding.scrollView2.smoothScrollTo(0, 0)
            overridePendingTransition(0, 0)
        }

        ibnMajahViewModel.IbnMajahLiveData.observe(this, Observer { hadithText ->
            binding.ibnmajahhadithTxtView.text = hadithText
        })
    }
}