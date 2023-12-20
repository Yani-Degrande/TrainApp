package com.example.trainapp.ui.trainOverview

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.trainapp.data.TrainComponent
import com.example.trainapp.ui.trainOverview.TrainUiState

class TrainViewModel : ViewModel() {
    private val _trainUiState = MutableStateFlow(TrainUiState(trains = TrainComponent.getAll()))
    val trainUiState = _trainUiState.asStateFlow()
}