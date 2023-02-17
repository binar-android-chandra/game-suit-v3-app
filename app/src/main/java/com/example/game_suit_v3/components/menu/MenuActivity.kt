package com.example.game_suit_v3.components.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import com.example.game_suit_v3.R
import com.example.game_suit_v3.databinding.ActivityMenuBinding
import com.example.game_suit_v3.components.game.GameActivity
import com.google.android.material.snackbar.Snackbar

class MenuActivity : AppCompatActivity() {
  private val binding: ActivityMenuBinding by lazy { ActivityMenuBinding.inflate(layoutInflater) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)

    supportActionBar?.hide()

    val username = intent.getStringExtra("username") ?: return

    setTextView(username)

    showSnackbar("Selamat Datang $username")

    binding.btnMenuPlayer.setOnClickListener {
      startGameActivity(username, true)
    }

    binding.btnMenuCom.setOnClickListener {
      startGameActivity(username, false)
    }
  }

  private fun setTextView(username: String) {
    binding.tvMenuPlayer.text = "$username vs Pemain"
    binding.tvMenuCom.text = "$username vs CPU"
  }

  private fun showSnackbar(message: String) {
    val snackbar = Snackbar.make(binding.root, message, Snackbar.LENGTH_INDEFINITE)
    snackbar.setAction(getString(R.string.tutup)) {
      snackbar.dismiss()
    }
    snackbar.show()
  }

  private fun startGameActivity(username: String, isVsPlayer: Boolean) {
    val intent = Intent(this, GameActivity::class.java)
    val bundle = bundleOf(
      "username" to username,
      "isVsPlayer" to isVsPlayer
    )
    intent.putExtras(bundle)
    startActivity(intent)
  }
}