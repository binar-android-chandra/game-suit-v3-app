package com.example.game_suit_v3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.game_suit_v3.components.landing.LandingActivity

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    supportActionBar?.hide()

    Looper.myLooper()?.let {
      Handler(it).postDelayed({
        val intent = Intent(this, LandingActivity::class.java)
        startActivity(intent)
        finish()
      }, 3000)
    }
  }
}