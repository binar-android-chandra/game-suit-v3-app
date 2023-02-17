package com.example.game_suit_v3.components.landing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.game_suit_v3.databinding.ActivityLandingBinding

class LandingActivity : AppCompatActivity() {
  private val binding: ActivityLandingBinding by lazy {
    ActivityLandingBinding.inflate(layoutInflater)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)

    supportActionBar?.hide()

    val dotsIndicator = binding.dotsIndicator
    val landingPagerAdapter = LandingPagerAdapter(supportFragmentManager)
    landingPagerAdapter.addFragment(LandingFragment1())
    landingPagerAdapter.addFragment(LandingFragment2())
    landingPagerAdapter.addFragment(LandingFragment3())

    binding.vpLanding.adapter = landingPagerAdapter
    dotsIndicator.attachTo(binding.vpLanding)
  }
}