package com.example.trainapp

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.trainapp.data.TrainComponent

class TrainViewModel : ViewModel() {
    private val _trainUiState = MutableStateFlow(TrainUiState(trains = TrainComponent.getAll()))
    val trainUiState = _trainUiState.asStateFlow()

    init {
        Log.i("TrainViewModel", "Creating new instance $this")
    }
}