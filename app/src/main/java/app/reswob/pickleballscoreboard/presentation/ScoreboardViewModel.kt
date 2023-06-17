package app.reswob.pickleballscoreboard.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class ScoreboardViewModel : ViewModel() {
    var playerOneScore = MutableStateFlow(0)
    var playerTwoScore = MutableStateFlow(0)

    public fun increasePlayerOneScore() {
        val newScore = playerOneScore.value + 1
        playerOneScore.update { newScore }

    }

    public fun decreasePlayerOneScore() {
        val newScore = playerOneScore.value - 1
        playerOneScore.update { newScore }
    }

    public fun increasePlayerTwoScore() {
        val newScore = playerTwoScore.value + 1
        playerTwoScore.update  { newScore }
    }

    public fun decreasePlayerTwoScore() {
        val newScore = playerTwoScore.value - 1
        playerTwoScore.update { newScore }
    }

    public fun resetScore() {
        playerOneScore.update { 0 }
        playerTwoScore.update { 0 }
    }
}