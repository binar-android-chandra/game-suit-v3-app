package com.example.game_suit_v3.components.landing

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.example.game_suit_v3.components.menu.MenuActivity
import com.example.game_suit_v3.databinding.FragmentLanding3Binding

class LandingFragment3 : Fragment() {
  private var _binding: FragmentLanding3Binding? = null
  private val binding: FragmentLanding3Binding
    get() {
      return _binding!!
    }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentLanding3Binding.inflate(inflater, container, false)
    return binding.root
  }


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.textLanding3.doAfterTextChanged {
      val count = binding.textLanding3.text.toString().length
      binding.btnLanding3.isEnabled = count > 3
    }

    binding.btnLanding3.setOnClickListener {
      startMenuActivity()
    }
  }

  private fun startMenuActivity() {
    val username = binding.textLanding3.text.toString()
    val intent = Intent(activity, MenuActivity::class.java)
    intent.putExtra("username", username)
    activity?.startActivity(intent)
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}