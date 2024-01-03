package com.example.trainapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import com.example.trainapp.ui.TrainApp
import com.example.trainapp.ui.theme.TrainAppTheme
import com.example.trainapp.ui.util.TrainNavigationType

/**
 * The main activity of the Train Application.
 * This activity sets up the UI for the application and handles the primary user interface.
 *
 * It uses the ExperimentalMaterial3WindowSizeClassApi for responsive layout design based on the window size.
 */
class MainActivity : ComponentActivity() {
    /**
     * Called when the activity is starting.
     * Sets up the content view with different navigation types based on the window size class.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     * then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     * Note: Otherwise it is null.
     */
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrainAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val windowSize = calculateWindowSizeClass(activity = this)
                    when (windowSize.widthSizeClass) {
                        WindowWidthSizeClass.Compact -> {
                            TrainApp(TrainNavigationType.BOTTOM_NAVIGATION)
                        }
                        WindowWidthSizeClass.Medium -> {
                            TrainApp(TrainNavigationType.NAVIGATION_RAIL)
                        }
                        WindowWidthSizeClass.Expanded -> {
                            TrainApp(TrainNavigationType.PERMANENT_NAVIGATION_DRAWER)
                        }
                        else -> {
                            TrainApp(TrainNavigationType.BOTTOM_NAVIGATION)
                        }
                    }
                }
            }
        }
    }
}
