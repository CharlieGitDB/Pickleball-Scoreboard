package app.reswob.pickleballscoreboard.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.ProvideTextStyle
import androidx.wear.compose.material.Text

@Composable
fun Scoreboard(
    playerOneState: Number,
    playerTwoState: Number,
    onIncreasePlayerOneScore: () -> Unit,
    onDecreasePlayerOneScore: () -> Unit,
    onIncreasePlayerTwoScore: () -> Unit,
    onDecreasePlayerTwoScore: () -> Unit,
    onReset: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = playerOneState.toString(),
                fontSize = 45.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = playerTwoState.toString(),
                fontSize = 45.sp,
                textAlign = TextAlign.Center
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            ScoreButtons(
                hasNoWinner(playerOneState, playerTwoState),
                onIncreasePlayerOneScore,
                onDecreasePlayerOneScore,
                onIncreasePlayerTwoScore,
                onDecreasePlayerTwoScore
            )
            WinnerMessage(pickWinner(playerOneState, playerTwoState))
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = onReset,
                modifier = Modifier.width(125.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray.copy(alpha = 0.3F))
            ) {
                Text(text = "Reset")
            }
        }
    }
}

private fun hasNoWinner(playerOneScore: Number, playerTwoScore: Number): Boolean {
    if (playerOneScore.toInt() < 11 && playerTwoScore.toInt() < 11) {
        return true;
    }

    return false;
}

private fun pickWinner(playerOneScore: Number, playerTwoScore: Number): Player? {
    if (playerOneScore.toInt() >= 11) {
        return Player.ONE;
    } else if (playerTwoScore.toInt() >= 11) {
        return Player.TWO;
    }

    return null;
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ScoreButtons(
    noWinner: Boolean,
    onIncreasePlayerOneScore: () -> Unit,
    onDecreasePlayerOneScore: () -> Unit,
    onIncreasePlayerTwoScore: () -> Unit,
    onDecreasePlayerTwoScore: () -> Unit
) {
    if (noWinner) {
        RoundedButton("T1 +", onIncreasePlayerOneScore, onDecreasePlayerOneScore)
        Spacer(modifier = Modifier.width(10.dp))
        RoundedButton("T2 +", onIncreasePlayerTwoScore, onDecreasePlayerTwoScore)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun RoundedButton(
    text: String,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {
    Column(verticalArrangement = Arrangement.Center) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(color = Color.DarkGray.copy(alpha = 0.3F))
                .width(75.dp)
        ) {
            ProvideTextStyle(value = MaterialTheme.typography.button) {
                Text(
                    text = text,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                        .background(color = Color.Transparent)
                        .combinedClickable(
                            onClick = onClick,
                            onLongClick = onLongClick
                        )
                )
            }
        }
    }
}

@Composable
private fun WinnerMessage(winner: Player?) {
    if (winner != null) {
        if (winner == Player.ONE) {
            Text(text = "T1 Wins!")
        } else if (winner == Player.TWO) {
            Text(text = "T2 Wins!")
        }
    }
}