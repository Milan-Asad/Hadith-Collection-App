package com.example.hadithcollection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
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

    // SETTING THE VIEW BINDING
    private lateinit var binding: ActivityTirmidhiCollectionBinding
    private lateinit var hadithTxt: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTirmidhiCollectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hadithTxt = findViewById(R.id.tirmidhihadithTxtView)

        binding.hadithBtn.setOnClickListener {
            getMyData()
        }
    }

    // MAKING THE FUNCTION TO RETRIEVE THE DATA
    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(API_Interface::class.java)

        // ATTACHING THE BUILT RETROFIT TO THE "GET" REQUEST PART
        val retrofitData = retrofitBuilder.getHadithData()

        retrofitData.enqueue(object : Callback<MyHadithData> {
            override fun onResponse(
                call: Call<MyHadithData>,
                response: Response<MyHadithData>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        val hadithData = responseBody.data
                        val hadithText = hadithData.hadith_english
                        binding.tirmidhihadithTxtView.text = hadithText
                    } else {
                        Log.d("TirmidhiCollection", "RESTART THE APP")
                    }
                }
            }

            override fun onFailure(call: Call<MyHadithData>, t: Throwable) {
                Log.d("TirmidhiCollection", "RESTART THE APP: ${t.message}")
            }
        })
    }
}
