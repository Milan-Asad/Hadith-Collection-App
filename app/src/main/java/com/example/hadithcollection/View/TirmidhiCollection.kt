package com.example.hadithcollection.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hadithcollection.Model.API_Interface
import com.example.hadithcollection.ViewModel.TirmidhiViewModel
import com.example.hadithcollection.databinding.ActivityTirmidhiCollectionBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

// https://random-hadith-generator.vercel.app/tirmidhi/- MAIN LINK

const val BASE_URL = "https://random-hadith-generator.vercel.app"

@AndroidEntryPoint
class TirmidhiCollection : AppCompatActivity() {
    //@Inject
    lateinit var tirmidhiViewModel: TirmidhiViewModel

    private lateinit var binding: ActivityTirmidhiCollectionBinding


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
