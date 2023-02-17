package com.example.game_suit_v3.components.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.widget.AppCompatImageButton
import com.example.game_suit_v3.R
import com.example.game_suit_v3.databinding.ActivityGameBinding
import java.util.*

class GameActivity : AppCompatActivity() {
  private val binding: ActivityGameBinding by lazy { ActivityGameBinding.inflate(layoutInflater) }
  private val viewModel = GameViewModel()
  private var playerOption: String? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)

    supportActionBar?.hide()

    val data = intent.extras
    val username = data?.getString("username") ?: return
    val isVsPlayer = data.getBoolean("isVsPlayer")

    binding.tvGameP1.text = username
    binding.tvGameP2.text = if (isVsPlayer) "Pemain 2" else "CPU"

    binding.btnRockP1.setOnClickListener { handleClickP1("ROCK", username, isVsPlayer) }
    binding.btnPaperP1.setOnClickListener { handleClickP1("PAPER", username, isVsPlayer) }
    binding.btnScissorP1.setOnClickListener { handleClickP1("SCISSOR", username, isVsPlayer) }

    binding.btnRefresh.setOnClickListener { restartGame() }
    binding.btnClose.setOnClickListener { onBackPressed() }

    if (!isVsPlayer) return
    binding.btnRockP2.setOnClickListener { handleClickP2("ROCK", username) }
    binding.btnPaperP2.setOnClickListener { handleClickP2("PAPER", username) }
    binding.btnScissorP2.setOnClickListener { handleClickP2("SCISSOR", username) }
  }

  private fun handleClickP1(option: String, username: String, isVsPlayer: Boolean) {
    val comOption = viewModel.pickRandomOption()
    startGameP1(option)

    if (isVsPlayer) {
      playerOption?.let { startGame(option, it, username, true) }
      playerOption = option
    } else {
      Looper.myLooper()?.let {
        Handler(it).postDelayed({
          startGameP2(comOption)
          startGame(option, comOption, username, false)
        }, 500)
      }
    }
  }

  private fun handleClickP2(option: String, username: String) {
    startGameP2(option)
    playerOption?.let { startGame(it, option, username, true) }
    playerOption = option
  }

  private fun startGameP2(option: String) {
    when (option) {
      "ROCK" -> setBackground(binding.btnRockP2)
      "PAPER" -> setBackground(binding.btnPaperP2)
      "SCISSOR" -> setBackground(binding.btnScissorP2)
    }
  }

  private fun startGameP1(option: String) {
    when (option) {
      "ROCK" -> setBackground(binding.btnRockP1)
      "PAPER" -> setBackground(binding.btnPaperP1)
      "SCISSOR" -> setBackground(binding.btnScissorP1)
    }
  }

  private fun startGame(optionP1: String, optionP2: String, username: String, isVsPlayer: Boolean) {
    Looper.myLooper()?.let {
      Handler(it).postDelayed({
        when {
          viewModel.isDraw(optionP1, optionP2) -> showDialog("Seri!")
          viewModel.isWin(optionP1, optionP2) == true -> showDialog("$username \n Menang")
          else -> showDialog("${if (isVsPlayer) "Pemain 2" else "CPU"} \n Menang")
        }
      }, 500)
    }
  }

   fun restartGame() {
    clearBackground(binding.btnRockP1)
    clearBackground(binding.btnRockP2)
    clearBackground(binding.btnScissorP1)
    clearBackground(binding.btnScissorP2)
    clearBackground(binding.btnPaperP1)
    clearBackground(binding.btnPaperP2)
    playerOption = null
  }

  private fun showDialog(message: String) {
    val dialogFragment = GameDialogFragment(message.uppercase(Locale.ROOT))
    dialogFragment.isCancelable = false
    dialogFragment.show(supportFragmentManager, "game-dialog")
  }

  private fun clearBackground(btn: AppCompatImageButton) {
    btn.setBackgroundColor(resources.getColor(R.color.white))
  }

  private fun setBackground(btn: AppCompatImageButton) {
    btn.setBackgroundResource(R.drawable.rounded)
  }
}