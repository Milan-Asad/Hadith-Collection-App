package com.hadithCollection.HadithCollection.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.hadithCollection.HadithCollection.R

class MainActivity : AppCompatActivity() {
    private val splashDuration: Long = 3000 // 3 SECONDS LONG

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // SPLASH SCREEN
        Handler().postDelayed({
            val intent = Intent(this, HadithSelection::class.java)
            startActivity(intent)
            finish()
        }, splashDuration)
    }
}