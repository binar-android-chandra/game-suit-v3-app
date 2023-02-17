package com.example.game_suit_v3.components.game

import kotlin.random.Random

class GameViewModel() {
  private val options = listOf("ROCK", "SCISSOR", "PAPER")

  private val rules = mapOf(
    "ROCK-SCISSOR" to true,
    "ROCK-PAPER" to false,
    "SCISSOR-PAPER" to true,
    "SCISSOR-ROCK" to false,
    "PAPER-ROCK" to true,
    "PAPER-SCISSOR" to false,
  )

  fun isDraw(from: String, to: String): Boolean = from == to

  fun isWin(from: String, to: String): Boolean? = rules["$from-$to"]

  fun pickRandomOption(): String = options[Random.nextInt(0, 3)]
}