package com.example.hadithcollection.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hadithcollection.R
import com.example.hadithcollection.ViewModel.MuslimViewModel
import com.example.hadithcollection.databinding.ActivityBukhariCollectionBinding
import com.example.hadithcollection.databinding.ActivityMuslimCollectionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MuslimCollection : AppCompatActivity() {

    // VIEW MODEL
    lateinit var muslimViewModel: MuslimViewModel

    // BINDING VAR
    lateinit var binding: ActivityMuslimCollectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMuslimCollectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        muslimViewModel = ViewModelProvider(this).get(MuslimViewModel::class.java)

        binding.muslimBtn.setOnClickListener {
            muslimViewModel.fetchMuslimHadith()
        }

        muslimViewModel.MuslimLiveData.observe(this, Observer { hadithText ->
            binding.muslimhadithTxtView.text = hadithText
        })

    }
}