package com.example.game_suit_v3.components.landing

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class LandingPagerAdapter(fragmentManager: FragmentManager) :
  FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
  private val fragments: MutableList<Fragment> = mutableListOf()

  fun addFragment(fragment: Fragment) {
    fragments.add(fragment)
  }

  override fun getCount(): Int {
    return fragments.size
  }

  override fun getItem(position: Int): Fragment {
    return fragments[position]
  }

}