package app.reswob.pickleballscoreboard.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import app.reswob.pickleballscoreboard.R
import app.reswob.pickleballscoreboard.presentation.theme.PickleballScoreboardTheme

class MainActivity : ComponentActivity()s {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = viewModel<ScoreboardViewModel>()
            val playerOneScore by viewModel.playerOneScore.collectAsStateWithLifecycle()
            val playerTwoScore by viewModel.playerTwoScore.collectAsStateWithLifecycle()

            Scaffold() {
                WatchFace(
                    playerOneState = playerOneScore,
                    playerTwoState = playerTwoScore,
                    onIncreasePlayerOneScore = viewModel::increasePlayerOneScore,
                    onIncreasePlayerTwoScore = viewModel::increasePlayerTwoScore,
                    onReset = viewModel::resetScore
                )
            }
        }
    }
}

@Composable
private fun WatchFace(
    playerOneState: Number,
    playerTwoState: Number,
    onIncreasePlayerOneScore: () -> Unit,
    onIncreasePlayerTwoScore: () -> Unit,
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
           Button(
               onClick = onIncreasePlayerOneScore,
               modifier = Modifier.width(75.dp)
           ) {
              Text(text = "P1 +")
           }
           Spacer(modifier = Modifier.width(10.dp))
           Button(
               onClick = onIncreasePlayerTwoScore,
               modifier = Modifier.width(75.dp)
           ) {
              Text(text = "P2 +")
           }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = onReset,
                modifier = Modifier.width(125.dp)
            ) {
                Text(text = "Reset")
            }
        }
    }
}