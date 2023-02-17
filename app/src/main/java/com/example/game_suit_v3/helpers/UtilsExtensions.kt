package com.example.game_suit_v3.helpers

import android.content.Context
import android.widget.Toast

fun Context.toast(message: String) {
  val toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
  toast.show()
}