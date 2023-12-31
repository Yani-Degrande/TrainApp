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
import com.example.trainapp.ui.util.TaskNavigationType

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrainAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val windowSize = calculateWindowSizeClass(activity = this)
                    when (windowSize.widthSizeClass) {
                        WindowWidthSizeClass.Compact -> {
                            TrainApp(TaskNavigationType.BOTTOM_NAVIGATION)
                        }
                        WindowWidthSizeClass.Medium -> {
                            TrainApp(TaskNavigationType.NAVIGATION_RAIL)
                        }
                        WindowWidthSizeClass.Expanded -> {
                            TrainApp(TaskNavigationType.PERMANENT_NAVIGATION_DRAWER)
                        }
                        else -> {
                            TrainApp(TaskNavigationType.BOTTOM_NAVIGATION)
                        }
                    }
                }
            }
        }
    }
}




