package app.reswob.pickleballscoreboard.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.ambient.AmbientModeSupport
import androidx.wear.compose.material.Scaffold

class MainActivity : FragmentActivity(), AmbientModeSupport.AmbientCallbackProvider {
    override fun getAmbientCallback(): AmbientModeSupport.AmbientCallback = MyAmbientCallback()

    private lateinit var ambientController: AmbientModeSupport.AmbientController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ambientController = AmbientModeSupport.attach(this)

        setContent {
            val viewModel = viewModel<ScoreboardViewModel>()
            val playerOneScore by viewModel.playerOneScore.collectAsStateWithLifecycle()
            val playerTwoScore by viewModel.playerTwoScore.collectAsStateWithLifecycle()

            Scaffold() {
                Scoreboard(
                    playerOneState = playerOneScore,
                    playerTwoState = playerTwoScore,
                    onIncreasePlayerOneScore = viewModel::increasePlayerOneScore,
                    onDecreasePlayerOneScore = viewModel::decreasePlayerOneScore,
                    onIncreasePlayerTwoScore = viewModel::increasePlayerTwoScore,
                    onDecreasePlayerTwoScore = viewModel::decreasePlayerTwoScore,
                    onReset = viewModel::resetScore
                )
            }
        }
    }

    private class MyAmbientCallback : AmbientModeSupport.AmbientCallback() {

        override fun onEnterAmbient(ambientDetails: Bundle?) {
            // Handle entering ambient mode
        }

        override fun onExitAmbient() {
            // Handle exiting ambient mode
        }

        override fun onUpdateAmbient() {
            // Update the content
        }
    }
}