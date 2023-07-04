package com.example.hadithcollection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hadithcollection.HadithResponse.MyHadithData
import com.example.hadithcollection.databinding.ActivityTirmidhiCollectionBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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