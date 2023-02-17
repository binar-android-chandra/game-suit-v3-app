package com.example.game_suit_v3.components.game

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.game_suit_v3.databinding.FragmentDialogBinding

class GameDialogFragment(private val message: String) : DialogFragment() {
  private var _binding: FragmentDialogBinding? = null
  private val binding: FragmentDialogBinding
    get() = _binding!!

  override fun onStart() {
    super.onStart()
    val width = 800
    val height = 800
    dialog?.window?.setLayout(width, height)
    dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentDialogBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.tvResult.text = message
    binding.btnRestart.setOnClickListener {
      dismiss()
      (activity as GameActivity).restartGame()
    }
    binding.btnBack.setOnClickListener {
      dismiss()
      activity?.onBackPressed()
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}