package app.reswob.pickleballscoreboard.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class ScoreboardViewModel : ViewModel() {
    var playerOneScore = MutableStateFlow(0)
    var playerTwoScore = MutableStateFlow(0)

    public fun increasePlayerOneScore() = increaseScore(playerOneScore)

    public fun decreasePlayerOneScore() = decreaseScore(playerOneScore)

    public fun increasePlayerTwoScore() = increaseScore(playerTwoScore)

    public fun decreasePlayerTwoScore() = decreaseScore(playerTwoScore)

    public fun resetScore() {
        playerOneScore.update { 0 }
        playerTwoScore.update { 0 }
    }

    private fun increaseScore(player: MutableStateFlow<Int>) {
        val newScore = player.value + 1
        player.update { newScore }
    }

    private fun decreaseScore(player: MutableStateFlow<Int>) {
        val newScore = player.value - 1
        if (newScore < 0) {
            return
        }

        player.update { newScore }
    }
}